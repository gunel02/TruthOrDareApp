package com.example.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.databinding.FragmentPlayGameBinding

class PlayGameFragment : Fragment() {

    private lateinit var binding : FragmentPlayGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPlayGameBinding.inflate(inflater, container, false)

        initListener()

        return binding.root

    }

    private fun initListener(){

    }

}