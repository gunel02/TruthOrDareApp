package com.example.app.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.app.R
import com.example.app.databinding.FragmentStartBinding
import com.example.app.fragment.questions.TrueQuestionsFragment
import com.example.app.view_model.PlayerViewModel

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val playerViewModel: PlayerViewModel by viewModels()

    private val questions = mutableListOf<String>()
    val name = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        initListener()
        displayName()

        return binding.root
    }
   /* private fun initListener() {

        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.trueButton.setOnClickListener{
            val fragment = StartFragment()
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }
    }*/
    private fun displayName() {
        playerViewModel.readAllData.observe(viewLifecycleOwner) { players ->
            val result = arguments?.getString("")
            binding.getName.text = result

            if (players.isNotEmpty()) {
                val currentPlayer = players[playerViewModel.currentPlayerIndex]
                binding.getName.text = "${currentPlayer.name}"
            }
        }
    }

    private fun initListener() {
        playerViewModel.readAllData.observe(viewLifecycleOwner) { players ->
            if (players.isNotEmpty()) {
                val currentPlayer = players[playerViewModel.currentPlayerIndex]
                binding.getName.text = "Player: ${currentPlayer.name}"
            }
        }

        binding.trueButton.setOnClickListener {
            val truthQuestions = playerViewModel.questionsModel?.truth ?: emptyList()
            if (truthQuestions.isNotEmpty()) {
                val question = truthQuestions[playerViewModel.currentQuestionIndex]?.question
                binding.getName.text = question

                playerViewModel.currentQuestionIndex =
                    (playerViewModel.currentQuestionIndex + 1) % truthQuestions.size
            }
        }

        binding.dareButton.setOnClickListener {
            val dareQuestions = playerViewModel.questionsModel?.dare ?: emptyList()
            if (dareQuestions.isNotEmpty()) {
                val task = dareQuestions[playerViewModel.currentQuestionIndex]?.question
                binding.getName.text = task // this sets to name - wrong

                playerViewModel.currentQuestionIndex =
                    (playerViewModel.currentQuestionIndex + 1) % dareQuestions.size
            }
        }

        binding.trueButton.setOnClickListener {
            navigateToTrueQuestionsFragment()
        }
    }

    private fun navigateToTrueQuestionsFragment() {
        val fragment = TrueQuestionsFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}







