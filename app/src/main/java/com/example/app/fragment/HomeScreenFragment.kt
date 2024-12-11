package com.example.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.app.R
import com.example.app.adapter.ViewPagerAdapter
import com.example.app.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)


        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicator.setViewPager(binding.viewPager)

//        val middlePosition = adapter.pageSize / 2
//        binding.viewPager.setCurrentItem(middlePosition, false)



        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.iconSettings.setOnClickListener {
            val fragment = SettingsFragment()
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }
        binding.iconGroup.setOnClickListener {
            val fragment = SetPlayersFragment()
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }
    }
}