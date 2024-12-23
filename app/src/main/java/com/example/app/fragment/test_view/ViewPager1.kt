package com.example.app.fragment.test_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.R
import com.example.app.databinding.FragmentViewPager1Binding
import com.example.app.fragment.SettingsFragment

class ViewPager1 : Fragment() {

    private lateinit var binding: FragmentViewPager1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentViewPager1Binding.inflate(inflater, container, false)


        binding.beginButton.setOnClickListener{
            val fragment = SettingsFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }

        return binding.root
    }

}