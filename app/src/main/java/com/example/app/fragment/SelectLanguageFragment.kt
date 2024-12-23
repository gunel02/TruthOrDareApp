@file:Suppress("DEPRECATION")

package com.example.app.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.databinding.FragmentSelectLanguageBinding
import com.example.app.helper.Const
import com.example.app.helper.SharedPreference
import java.util.Locale

class SelectLanguageFragment : Fragment() {

    private lateinit var binding: FragmentSelectLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectLanguageBinding.inflate(inflater, container, false)

        loadLocale()
        setTexts()
        initListener()

        return binding.root
    }

    private fun setLocale(language: String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = Configuration()
        config.locale = local
        val baseContext = requireContext()
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        SharedPreference.setLang(language)
        setTexts()
    }

    private fun loadLocale() {
        val savedLang = SharedPreference.getLang() ?: Const.RUSSIAN_LANG
        setLocale(savedLang)
    }

    private fun setTexts() {
        binding.textChooseLanguage.text = getString(R.string.text_choose_language)
        binding.englishLanguageText.text = getString(R.string.text_english)
        binding.russianLanguageText.text = getString(R.string.text_russian)
        binding.turkmenLanguageText.text = getString(R.string.text_turkmen)
    }

    private fun initListener() {
        binding.russianLanguageButton.setOnClickListener {
            setLocale(Const.RUSSIAN_LANG)
            navigateToOnBoardingFragment()
        }

        binding.englishLanguageButton.setOnClickListener {
            setLocale(Const.ENGLISH_LANG)
            navigateToOnBoardingFragment()
        }

        binding.turkmenLanguageButton.setOnClickListener {
            setLocale(Const.TURKMEN_LANG)
            navigateToOnBoardingFragment()
        }
    }

    private fun navigateToOnBoardingFragment() {
        val fragment = OnBoardingFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}