package com.example.app.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app.R
import com.example.app.adapter.PlayersNameAdapter
import com.example.app.data_class.PlayersData
import com.example.app.databinding.FragmentSetPlayersBinding
import com.example.app.view_model.PlayerViewModel
import kotlinx.coroutines.launch

class SetPlayersFragment : Fragment() {


    private var _binding: FragmentSetPlayersBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { PlayersNameAdapter() }

    private val playerViewModel: PlayerViewModel by viewModels()


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetPlayersBinding.inflate(inflater, container, false)

        playerViewModel.readAllData.observe(viewLifecycleOwner) { players ->
            adapter.setData(players)
        }

//        initListener()
        setUpRecyclerView()

        deletePlayer()

        addUserName()

        initListener()

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
    }

    private fun addUserName() {
            lifecycleScope.launch {
                playerViewModel.addUser(playersData = PlayersData(0, ""))
            }
    }

    private fun initListener() {

        binding.floatingActionButtonPlus.setOnClickListener{
            addUserName()
        }

        binding.floatingActionButtonPlay.setOnClickListener {
            val fragment = LightFragment()
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
            transaction.commit()
        }

    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    private fun deletePlayer() {
        adapter.setOnDeleteClickListener { player ->
            playerViewModel.deleteUser(player)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}