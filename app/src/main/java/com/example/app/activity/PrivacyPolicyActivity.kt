package com.example.app.activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.progressBar.progress = progress * 100
            }
        }


        val url = intent.getStringExtra("URL_KEY")
        if (url != null) {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.loadUrl(url)
        }

        initListener()

    }

    private fun initListener() {
        binding.backButton.setOnClickListener {
            finish()
        }

    }
}