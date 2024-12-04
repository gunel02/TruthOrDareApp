package com.example.app.app

import android.app.Application
import com.example.app.helper.SharedPreference

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreference.init(this@App)
    }
}