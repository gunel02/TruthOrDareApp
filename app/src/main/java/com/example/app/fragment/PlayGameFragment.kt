package com.example.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.databinding.FragmentPlayGameBinding

class PlayGameFragment : Fragment() {

    private lateinit var binding: FragmentPlayGameBinding
    private var userName = ""
    private var gameText = ""
    private var gameMode = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPlayGameBinding.inflate(inflater, container, false)

        getData()
        setData()
        initListener()

        return binding.root

    }

    private fun setData() {
        binding.setName.text = userName
        binding.gameText.text = gameText
    }

    private fun getData() {
        userName = arguments?.getString("user_name") ?: ""
        gameText = arguments?.getString("game_text") ?: ""
        gameMode = arguments?.getString("mode") ?: ""
    }

    private fun initListener() {
        binding.finishBtn.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}