package com.example.app.fragment.test_view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TestViewpagerAdapter(private val testHomeFragment: TestHomeFragment) :
    FragmentStateAdapter(testHomeFragment) {

    private val pageSize = 3

    override fun getItemCount(): Int {
        return this.pageSize
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ViewPager1()
            1 -> ViewPager2()
            2 -> ViewPager3()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}

