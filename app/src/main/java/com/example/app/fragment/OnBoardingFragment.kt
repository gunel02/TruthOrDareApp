package com.example.app.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.app.activity.HomeActivity
import com.example.app.adapter.ViewPagerAdapter
import com.example.app.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener() {
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicator.setViewPager(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == adapter.itemCount - 1) {
                    binding.skipButton.visibility = View.GONE
                    binding.skipButton.isClickable = false
                } else {
                    binding.skipButton.visibility = View.VISIBLE
                    binding.skipButton.isClickable = true
                }

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
            startActivity(Intent(activity, HomeActivity::class.java))
            activity?.finish()
        }

        binding.skipButton.setOnClickListener {
////            todo eger on boarding 3 fragmentda bolsa "SKIP" botton will be invisible and do not touchable - done
            binding.viewPager.setCurrentItem(adapter.itemCount - 1, true)
        }
    }

}

