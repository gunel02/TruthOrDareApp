package com.example.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.app.R
import com.example.app.databinding.FragmentWelcomeToAppBinding

class WelcomeToAppFragment : Fragment() {


    private lateinit var binding: FragmentWelcomeToAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeToAppBinding.inflate(inflater, container, false)


        return binding.root
    }

}