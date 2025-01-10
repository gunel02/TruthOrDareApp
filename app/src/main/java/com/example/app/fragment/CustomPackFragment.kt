package com.example.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.adapter.CustomPackAdapter
import com.example.app.data_class.EntityPlayers
import com.example.app.databinding.FragmentCustomPackBinding

class CustomPackFragment : Fragment() {

    private lateinit var binding: FragmentCustomPackBinding
    var customList: MutableList<EntityPlayers> = mutableListOf()
    private val adapter by lazy { CustomPackAdapter(this@CustomPackFragment) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomPackBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root

    }
    private fun setUpRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = adapter
    }


    fun deletePlayer(player: EntityPlayers) {
        customList.remove(player)
        adapter.setData()
    }
}