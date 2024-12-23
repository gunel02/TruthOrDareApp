package com.example.app.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app.fragment.OnBoardingFragment
import com.example.app.fragment.view_pager_fragments.OnboardingStep2Fragment
import com.example.app.fragment.view_pager_fragments.OnboardingStep3Fragment
import com.example.app.fragment.view_pager_fragments.OnboardingStep1Fragment

class ViewPagerAdapter(private val onBoardingFragment: OnBoardingFragment) :
    FragmentStateAdapter(onBoardingFragment) {

    private val pageSize = 3

    override fun getItemCount(): Int {
        return this.pageSize
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingStep1Fragment()
            1 -> OnboardingStep2Fragment()
            2 -> OnboardingStep3Fragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}

