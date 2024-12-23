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
import com.example.app.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.iconLeftArrow.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.information.setOnClickListener{
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val exitDialog = Dialog(requireContext())
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        exitDialog.setCancelable(false)
        exitDialog.setContentView(R.layout.layout_alert_dialog)
        exitDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        exitDialog.show()

        val informationDialog: View = exitDialog.findViewById(R.id.information_dialog)
        informationDialog.visibility = View.VISIBLE
        val yesButton: TextView = exitDialog.findViewById(R.id.yes_button)
        yesButton.setOnClickListener {
            informationDialog.visibility = View.GONE
            exitDialog.dismiss()
        }
    }
}