package com.example.app.fragment.viepager_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.databinding.FragmentLightBinding

class LightFragment : Fragment() {
    private lateinit var binding: FragmentLightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLightBinding.inflate(inflater, container, false)
        return binding.root

    }



}