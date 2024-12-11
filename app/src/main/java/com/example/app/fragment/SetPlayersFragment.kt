package com.example.app.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.adapter.PlayersNameAdapter
import com.example.app.data_class.PlayersData
import com.example.app.databinding.FragmentSetPlayersBinding
import com.example.app.view_model.PlayerViewModel
import kotlinx.coroutines.launch


class SetPlayersFragment : Fragment() {

    private var _binding: FragmentSetPlayersBinding? = null
    private val binding get() = _binding!!

    var usersList: MutableList<PlayersData> = mutableListOf()

    private val adapter by lazy { PlayersNameAdapter(this@SetPlayersFragment) }

    private val playerViewModel: PlayerViewModel by viewModels()

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSetPlayersBinding.inflate(inflater, container, false)

        playerViewModel.readAllData.observe(viewLifecycleOwner) { players ->
            usersList.clear()
            usersList.addAll(players)
            adapter.setData(usersList)

            if (usersList.isEmpty()) {
                usersList.add(PlayersData(0, ""))
                adapter.notifyDataSetChanged()
            }
        }

        setUpRecyclerView()
        initListener()
        return binding.root
    }

    private fun showAlertDialog() {
        val exitDialog = Dialog(requireContext())
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        exitDialog.setCancelable(false)
        exitDialog.setContentView(R.layout.layout_alert_dialog)
        exitDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        exitDialog.show()

        val error: View = exitDialog.findViewById(R.id.error_dialog)
        error.visibility = View.VISIBLE

        val okButton: TextView = exitDialog.findViewById(R.id.ok_button)
        okButton.setOnClickListener {
            exitDialog.dismiss()
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addUserName(playerData: PlayersData) {
        lifecycleScope.launch {
            playerViewModel.addUser(playerData)
        }
    }

    fun updatePlayer(playerData: PlayersData) {
        lifecycleScope.launch {
            playerViewModel.updateUser(playerData)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListener() {
        binding.floatingActionButtonPlus.setOnClickListener {
            val newPlayer = PlayersData(0, "")
            usersList.add(newPlayer)
            adapter.notifyItemInserted(usersList.size - 1)
            addUserName(newPlayer)
        }

        binding.floatingActionButtonPlay.setOnClickListener {
            if (areAllPlayerNamesValid()) {
                usersList.forEach { playerData ->
                    updatePlayer(playerData)
                }
                navigateToNextFragment()
            } else {
                showAlertDialog()
            }
        }
    }

    private fun areAllPlayerNamesValid(): Boolean {
        return usersList.all { !TextUtils.isEmpty(it.name) }
    }

    private fun navigateToNextFragment() {
        val fragment = HomeScreenFragment()
        val transaction: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
        transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
        transaction.commit()
    }

    fun deletePlayer(player: PlayersData) {
        playerViewModel.deleteUser(player)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
