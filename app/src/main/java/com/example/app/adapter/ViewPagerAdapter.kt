package com.example.app.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.app.fragment.HomeScreenFragment
import com.example.app.fragment.viepager_fragments.CustomPackFragment
import com.example.app.fragment.viepager_fragments.FriendlyFireFragment
import com.example.app.fragment.viepager_fragments.IceBreakingFragment
import com.example.app.fragment.viepager_fragments.LevelOfLegendsFragment
import com.example.app.fragment.viepager_fragments.PartyModeFragment
import com.example.app.fragment.viepager_fragments.Under18Fragment

class ViewPagerAdapter(private val homeScreenFragment: HomeScreenFragment) :
    FragmentStateAdapter(homeScreenFragment) {

    private val pageSize = 6

    override fun getItemCount(): Int {
        return this.pageSize
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FriendlyFireFragment()
            1 -> IceBreakingFragment()
            2 -> LevelOfLegendsFragment()
            3 -> PartyModeFragment()
            4 -> Under18Fragment()
            5 -> CustomPackFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}

