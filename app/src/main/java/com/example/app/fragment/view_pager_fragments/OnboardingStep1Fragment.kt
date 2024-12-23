package com.example.app.fragment.view_pager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.databinding.FragmentOnboardingStep1Binding

class OnboardingStep1Fragment : Fragment() {

    private lateinit var binding: FragmentOnboardingStep1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingStep1Binding.inflate(inflater, container, false)
        return binding.root
    }
}