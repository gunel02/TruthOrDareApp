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
import com.example.app.activity.HomeActivity
import com.example.app.data_class.EntityPlayers
import com.example.app.databinding.FragmentSelectGameModeBinding
import com.example.app.helper.Const
import com.example.app.view_model.PlayerViewModel
import kotlinx.coroutines.launch

class SelectGameModeFragment : Fragment() {

    private lateinit var binding: FragmentSelectGameModeBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private var usersList: MutableList<EntityPlayers> = mutableListOf()
    private var currentQueue = 0
    private var questionQueue = 0
    private var dareQueue = 0
    private var questionList: MutableList<String?> = mutableListOf()
    private var dareList: MutableList<String?> = mutableListOf()
    private var currentUserName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectGameModeBinding.inflate(inflater, container, false)

        loadQuestionsAndDares()
        getUsersData()
        initListener()

        return binding.root
    }

    private fun loadQuestionsAndDares() {
        val activity = requireActivity() as? HomeActivity
        val questionsModel = activity?.questionsModel
        val level = arguments?.getString("level") ?: ""

        when (level) {
            Const.LEVEL_EASY -> {
                questionsModel?.truth?.filter { it?.level == "Easy" }?.let { truths ->
                    questionList = truths.map { it?.question }.toMutableList()
                }
                questionsModel?.dare?.filter { it?.level == "Easy" }?.let { dares ->
                    dareList = dares.map { it?.question }.toMutableList()
                }
            }

            Const.LEVEL_EXTREME -> {
                questionsModel?.truth?.filter { it?.level == "Extreme" }?.let { truths ->
                    questionList = truths.map { it?.question }.toMutableList()
                }
                questionsModel?.dare?.filter { it?.level == "Extreme" }?.let { dares ->
                    dareList = dares.map { it?.question }.toMutableList()
                }
            }

            Const.LEVEL_HARD -> {
                questionsModel?.truth?.filter { it?.level == "Hard" }?.let { truths ->
                    questionList = truths.map { it?.question }.toMutableList()
                }
                questionsModel?.dare?.filter { it?.level == "Hard" }?.let { dares ->
                    dareList = dares.map { it?.question }.toMutableList()
                }
            }

            Const.LEVEL_CRAZY -> {
                questionsModel?.truth?.filter { it?.level == "Crazy" }?.let { truths ->
                    questionList = truths.map { it?.question }.toMutableList()
                }
                questionsModel?.dare?.filter { it?.level == "Crazy" }?.let { dares ->
                    dareList = dares.map { it?.question }.toMutableList()
                }
            }

            Const.LEVEL_CUSTOM -> {
                // Add logic for custom levels if needed
            }
        }
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

        bundle.putStringArrayList("questions_list", ArrayList(questionList))
        bundle.putStringArrayList("dares_list", ArrayList(dareList))

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


        bundle.putStringArrayList("questions_list", ArrayList(questionList))
        bundle.putStringArrayList("dares_list", ArrayList(dareList))


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