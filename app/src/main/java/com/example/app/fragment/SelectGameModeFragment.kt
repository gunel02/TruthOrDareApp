package com.example.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.R
import com.example.app.databinding.FragmentSelectGameModeBinding

class SelectGameModeFragment : Fragment() {

    private lateinit var binding: FragmentSelectGameModeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentSelectGameModeBinding.inflate(inflater, container, false)

        initListener()


        val playerNames = arguments?.getStringArrayList("PLAYER_NAMES")
        if (playerNames != null) {
            binding.setName.text = playerNames.sorted().joinToString(", ")
        }

        return binding.root
    }


    private fun initListener(){
        binding.backButton.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.truthButton.setOnClickListener{
            val fragment = PlayGameFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }
    }

}