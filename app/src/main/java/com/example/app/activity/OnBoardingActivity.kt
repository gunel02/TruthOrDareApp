package com.example.app.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.ActivityOnBoardingBinding
import com.example.app.fragment.SelectLanguageFragment

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
//
//        setContentView(binding.root)
//        val intent = Intent(this, SelectLanguageActivity::class.java)
//        startActivity(intent)
//

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)

        val fragment = SelectLanguageFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()
//        val intent = Intent(this, SelectLanguageActivity::class.java)
//        startActivity(intent)

        setContentView(binding.root)
    }
}




