package com.example.app.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.ActivityOnBoardingBinding
import com.example.app.databinding.ActivitySelectLanguageBinding
import com.example.app.databinding.ActivitySplashScreenBinding
import com.example.app.fragment.OnBoardingFragment
import com.example.app.fragment.SelectLanguageFragment
import com.example.app.helper.Const
import com.example.app.helper.SharedPreference
import java.util.Locale

class SelectLanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadLocale()
        setTexts()
        initListener()


    }

    private fun setLocale(language: String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = Configuration()
        config.locale = local
        val baseContext = this
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
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()

    }
}