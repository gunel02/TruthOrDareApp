package com.example.app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.app.R
import com.example.app.data_class.EntityPlayers
import com.example.app.databinding.FragmentSelectGameModeBinding
import com.example.app.helper.Const
import com.example.app.view_model.PlayerViewModel
import kotlinx.coroutines.launch

class SelectGameModeFragment : Fragment() {

    private lateinit var binding: FragmentSelectGameModeBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    var usersList: MutableList<EntityPlayers> = mutableListOf()
    var currentQueue = 0
    var questionQueue = 0
    var dareQueue = 0
    var questionList: MutableList<String> = mutableListOf()
    var dareList: MutableList<String> = mutableListOf()
    var currentUserName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSelectGameModeBinding.inflate(inflater, container, false)

        testAddQuestionsAndDares()
        getUsersData()
        initListener()

        return binding.root
    }

    private fun testAddQuestionsAndDares() {
        questionList.add("1")
        questionList.add("2")
        questionList.add("3")
        questionList.add("4")
        questionList.add("5")

        dareList.add("q")
        dareList.add("w")
        dareList.add("e")
        dareList.add("r")
        dareList.add("t")
    }


    private fun initListener() {
        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.truthButton.setOnClickListener {
            truthClick()
        }

        binding.dareButton.setOnClickListener {
            dareClick()
        }

        binding.randomChoiceButton.setOnClickListener {
            val random = (0..1).random()
            if (random == 0) {
                truthClick()
            } else {
                dareClick()
            }
        }
    }

    private fun truthClick() {
        val fragment = PlayGameFragment()
        val bundle = Bundle()
        bundle.putString("mode", Const.TRUTH)
        bundle.putString("user_name", currentUserName)
        if (questionQueue >= questionList.size) {
            questionQueue = 0
        }
        bundle.putString("game_text", questionList[questionQueue])
        questionQueue++
        currentQueue++
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.tag)
            .commit()

        setUserName()
    }

    private fun dareClick() {
        val fragment = PlayGameFragment()
        val bundle = Bundle()
        bundle.putString("mode", Const.DARE)
        bundle.putString("user_name", currentUserName)
        if (dareQueue >= dareList.size) {
            dareQueue = 0
        }
        bundle.putString("game_text", dareList[dareQueue])
        dareQueue++
        currentQueue++
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.tag)
            .commit()

        setUserName()
    }

    private fun getUsersData() {
        lifecycleScope.launch {
            try {
                val players = playerViewModel.getUsersWithoutLiveData()
                usersList.clear()
                usersList.addAll(players)

                setUserName()
            } catch (e: Exception) {
                Log.e("TAG_error", "getUsersData: " + e.message)
            }
        }
    }

    private fun setUserName() {
        currentUserName = usersList[currentQueue % usersList.size].name
        binding.setName.text = currentUserName
    }


}