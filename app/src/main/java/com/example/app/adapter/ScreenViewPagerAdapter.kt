package com.example.app.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app.fragment.LetsStartFragment
import com.example.app.fragment.OnBoardingFragment
import com.example.app.fragment.WelcomeToAppFragment
import com.example.app.fragment.viepager_fragments.IntroductionFragment

class ScreenViewPagerAdapter(private val onBoardingFragment: OnBoardingFragment) :
    FragmentStateAdapter(onBoardingFragment) {

    private val pageSize = 3

    override fun getItemCount(): Int {
        return this.pageSize
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WelcomeToAppFragment()
            1 -> IntroductionFragment()
            2 -> LetsStartFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}

