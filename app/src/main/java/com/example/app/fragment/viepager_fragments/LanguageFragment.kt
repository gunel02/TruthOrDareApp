@file:Suppress("DEPRECATION")

package com.example.app.fragment.viepager_fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.FragmentLanguageBinding
import com.example.app.fragment.MainFragment
import com.example.app.helper.Const
import com.example.app.helper.SharedPreference
import java.util.Locale

class LanguageFragment : Fragment() {

    private lateinit var binding : FragmentLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLanguageBinding.inflate(inflater, container, false)

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

        saveLanguagePreference(language)
        setTexts()
    }

    private fun loadLocale() {
        val savedLang = SharedPreference.getLang() ?: Const.ENGLISH_LANG
        setLocale(savedLang)
    }

    private fun saveLanguagePreference(language: String) {
        SharedPreference.setLang(language)
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
            navigateToMainFragment()
        }
        binding.englishLanguageButton.setOnClickListener {
            setLocale(Const.ENGLISH_LANG)
            navigateToMainFragment()
        }
        binding.turkmenLanguageButton.setOnClickListener {
            setLocale(Const.TURKMEN_LANG)
            navigateToMainFragment()
        }
    }

    private fun navigateToMainFragment(){
        val fragment = MainFragment()
        val transaction: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
        transaction.commit()

    }
}