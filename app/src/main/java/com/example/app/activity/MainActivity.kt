package com.example.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.ActivityMainBinding
import com.example.app.fragment.SetPlayersFragment
import com.example.app.fragment.viepager_fragments.LanguageFragment
import com.example.app.helper.SharedPreference


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initListener()

    }

    private fun init() {
        if (SharedPreference.getIsFirstOpen()) {
            SharedPreference.setIsFirstOpen(false)
//            todo go to add people fragment

            val fragment = SetPlayersFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

        } else {
//            todo go to add HomeFragment
        }
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun initListener() {
//        val fragment = LanguageFragment()
//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment)
//        transaction.commit()

    }
}