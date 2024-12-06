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

    private var titleList = mutableListOf<String>()
    private var descriptionList = mutableListOf<String>()
    private var imageList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        postToList()

//        val adapter = ViewPagerAdapter(titleList, descriptionList, imageList)
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicator.setViewPager(binding.viewPager)

        val middlePosition = titleList.size / 2
        binding.viewPager.setCurrentItem(middlePosition, false)



        initListener()
        return binding.root
    }


    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title)
        descriptionList.add(description)
        imageList.add(image.toString())
    }

    private fun postToList() {
        val titles = resources.getStringArray(R.array.titles)
        val descriptions = resources.getStringArray(R.array.descriptions)
        val images = resources.obtainTypedArray(R.array.images)

        for (i in titles.indices) {
            titleList.add(titles[i])
            descriptionList.add(descriptions[i])
            imageList.add(images.getResourceId(i, -1).toString())
        }
        images.recycle()
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