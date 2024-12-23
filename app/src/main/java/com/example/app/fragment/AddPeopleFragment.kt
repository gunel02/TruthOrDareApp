package com.example.app.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.adapter.PlayersNameAdapter
import com.example.app.data_class.EntityPlayers
import com.example.app.databinding.FragmentAddPeopleBinding
import com.example.app.view_model.PlayerViewModel
import kotlinx.coroutines.launch


class AddPeopleFragment : Fragment() {

    private var binding: FragmentAddPeopleBinding? = null

    var usersList: MutableList<EntityPlayers> = mutableListOf()

    private val adapter by lazy { PlayersNameAdapter(this@AddPeopleFragment) }

    private val playerViewModel: PlayerViewModel by viewModels()

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {

        binding = FragmentAddPeopleBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        getUsersData()
        initListener()

        return binding?.root
    }

    private fun getUsersData() {
        lifecycleScope.launch {
            try {
                val players = playerViewModel.getUsersWithoutLiveData()
                usersList.clear()
                usersList.addAll(players)

                while (usersList.size < 2) {
                    usersList.add(EntityPlayers(0, ""))
                }
                adapter.setData()
            } catch (e: Exception) {
                Log.e("TAG_error", "getUsersData: " + e.message)
            }
        }
    }

    private fun showAlertDialog() {
        val exitDialog = Dialog(requireContext())
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        exitDialog.setCancelable(true)
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
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = adapter
    }

    private fun addUserName(playerData: EntityPlayers) {
        lifecycleScope.launch {
            playerViewModel.addUser(playerData)
        }
    }

    private fun updatePlayer() {
        lifecycleScope.launch {
            playerViewModel.addAllPlayers(usersList)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListener() {
        binding?.addButton?.setOnClickListener {
            val newPlayer = EntityPlayers(0, "")
            usersList.add(newPlayer)
            adapter.setData()
        }

        binding?.playButton?.setOnClickListener {
            if (areAllPlayerNamesValid()) {
                updatePlayer()
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
        val fragment = HomeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    fun deletePlayer(player: EntityPlayers) {
        usersList.remove(player)
        adapter.setData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
