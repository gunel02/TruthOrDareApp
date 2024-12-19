package com.example.app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.app.R
import com.example.app.adapter.ScreenViewPagerAdapter
import com.example.app.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener() {
        val adapter = ScreenViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicator.setViewPager(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == adapter.itemCount - 1) {
                    binding.circleButton.visibility = View.GONE
                    binding.squareButton.visibility = View.VISIBLE
                } else {
                    binding.circleButton.visibility = View.VISIBLE
                    binding.squareButton.visibility = View.GONE
                }
            }
        })

        binding.circleButton.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < adapter.itemCount - 1) {
                binding.viewPager.setCurrentItem(currentItem + 1, true)
            }
        }

        binding.squareButton.setOnClickListener {
            val fragment = SetPlayersFragment()
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }
    }

}

