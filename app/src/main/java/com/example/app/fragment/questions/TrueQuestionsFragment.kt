package com.example.app.fragment.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.app.databinding.FragmentTrueQuestionsBinding
import com.example.app.view_model.PlayerViewModel
import kotlinx.coroutines.launch

class TrueQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentTrueQuestionsBinding
    private val playerViewModel: PlayerViewModel by viewModels({ requireActivity() })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrueQuestionsBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener(){
        binding.backButton.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}