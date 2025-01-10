package com.example.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.ActivityHomeBinding
import com.example.app.fragment.AddPeopleFragment
import com.example.app.fragment.HomeFragment
import com.example.app.helper.SharedPreference
import com.example.app.models.GetQuestionsModel
import com.example.app.utility.Utils


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    var questionsModel: GetQuestionsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestionsFromAssets()
        init()

    }

    private fun loadQuestionsFromAssets() {
        questionsModel = Utils.parseQuestionsJSON(this)
    }

    private fun init() {
        if (SharedPreference.getIsFirstOpen()) {
            SharedPreference.setIsFirstOpen(false)

            val fragment = AddPeopleFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

        } else {

            val fragment = HomeFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

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
}