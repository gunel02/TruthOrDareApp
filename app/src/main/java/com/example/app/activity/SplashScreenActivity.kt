package com.example.app.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ActivitySplashScreenBinding
import com.example.app.helper.SharedPreference

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
                    if (SharedPreference.getIsFirstOpen()) {
                        startActivity(
                            Intent(
                                this@SplashScreenActivity,
                                OnBoardingActivity::class.java
                            )
                        )
                    } else {
                        startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                    }
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
