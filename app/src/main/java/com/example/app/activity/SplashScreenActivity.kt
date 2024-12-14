//package com.example.app.activity
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import androidx.appcompat.app.AppCompatActivity
//import com.example.app.R
//import com.example.app.databinding.ActivityMainBinding
//import com.example.app.databinding.ActivitySplashScreenBinding
//
//@SuppressLint("CustomSplashScreen")
//class SplashScreenActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivitySplashScreenBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
////        setContentView(R.layout.activity_splash_screen)
////
////        startActivity(Intent(this, MainActivity::class.java))
////
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }, 6000)
//    }
//}

package com.example.app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        simulateProgress()
    }

    private fun simulateProgress() {
        val progressInterval = 50
        val totalDuration = 1000
        val maxProgress = binding.progressBar.max
        val increment = maxProgress / (totalDuration / progressInterval)

        var currentProgress = 0

        val runnable = object : Runnable {
            override fun run() {
                if (currentProgress < maxProgress) {
                    currentProgress += increment
                    binding.progressBar.progress = currentProgress
                    handler.postDelayed(this, progressInterval.toLong())
                } else {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
