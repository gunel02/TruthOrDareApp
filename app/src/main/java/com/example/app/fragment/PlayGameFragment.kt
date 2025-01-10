package com.example.app.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.databinding.FragmentPlayGameBinding
import com.example.app.helper.Const

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
        binding.changeQuestionsButton.setOnClickListener {
            changeQuestion()
        }

        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        binding.addCustomPackButton.setOnClickListener{
            showAlertDialog()
        }
    }

    private fun changeQuestion() {
        val questionsList = arguments?.getStringArrayList("questions_list")
        val daresList = arguments?.getStringArrayList("dares_list")

        if (gameMode == Const.TRUTH) {
            if (!questionsList.isNullOrEmpty()) {
                val currentIndex = questionsList.indexOf(gameText)
                val nextIndex = (currentIndex + 1) % questionsList.size
                gameText = questionsList[nextIndex]
            }
        } else if (gameMode == Const.DARE) {
            if (!daresList.isNullOrEmpty()) {
                val currentIndex = daresList.indexOf(gameText)
                val nextIndex = (currentIndex + 1) % daresList.size
                gameText = daresList[nextIndex]
            }
        }
        binding.gameText.text = gameText
    }

    private fun showAlertDialog() {
        val exitDialog = Dialog(requireContext())
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        exitDialog.setCancelable(false)
        exitDialog.setContentView(R.layout.layout_alert_dialog)
        exitDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        exitDialog.show()

        val informationDialog: View = exitDialog.findViewById(R.id.custom_dialog)
        informationDialog.visibility = View.VISIBLE
        val yesButton: TextView = exitDialog.findViewById(R.id.agree_button)
        yesButton.setOnClickListener {
            informationDialog.visibility = View.GONE
            exitDialog.dismiss()
        }
        val notAgreeButton: TextView = exitDialog.findViewById(R.id.not_agree_button)
        notAgreeButton.setOnClickListener {
            informationDialog.visibility = View.GONE
            exitDialog.dismiss()
        }


    }
}
