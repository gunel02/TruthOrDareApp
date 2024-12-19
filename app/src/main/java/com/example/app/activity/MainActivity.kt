package com.example.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.ActivityMainBinding
import com.example.app.fragment.viepager_fragments.LanguageFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

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