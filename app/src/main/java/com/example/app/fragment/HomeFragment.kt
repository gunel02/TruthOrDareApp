package com.example.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.HomeAdapter
import com.example.app.adapter.HomeList
import com.example.app.databinding.FragmentHomeBinding
import com.example.app.helper.Const

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        homeAdapter = HomeAdapter(this, getItems())
        binding.recyclerView.adapter = homeAdapter

        initListener()

        return binding.root
    }

    private fun getItems(): List<HomeList> {
        val searchList = mutableListOf<HomeList>()
        searchList.add(HomeList(R.string.text_fun, R.drawable.icon_rainbow, Const.LEVEL_EASY))
        searchList.add(HomeList(R.string.text_crazy, R.drawable.icon_fire, Const.LEVEL_CRAZY))
        searchList.add(
            HomeList(
                R.string.text_extreme, R.drawable.icon_confetti, Const.LEVEL_EXTREME
            )
        )
        searchList.add(HomeList(R.string.text_party_mode, R.drawable.icon_cheers, Const.LEVEL_HARD))
        searchList.add(
            HomeList(
                R.string.text_custom_pack, R.drawable.icon_wrench, Const.LEVEL_CUSTOM
            )
        )
        return searchList
    }


    private fun initListener() {
        binding.addPlayerButton.setOnClickListener {
            val fragment = AddPeopleFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment).addToBackStack(fragment.tag).commit()
        }

        binding.settingsButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, SettingsFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
    }
}