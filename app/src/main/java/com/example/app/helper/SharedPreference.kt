package com.example.app.helper

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {

    private const val SHARED_PREFERENCES_NAME = "MY_SHARED_PREFERENCES"
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    private const val KEY_LANGUAGE = "language"
    private const val KEY_IS_FIRST_OPEN = "is_first_open"

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun setLang(language: String) {
        editor.putString(KEY_LANGUAGE, language).apply()
    }

    fun getLang(): String? {
        return sharedPreferences.getString(KEY_LANGUAGE, Const.ENGLISH_LANG)
    }

    fun setIsFirstOpen(isFirst: Boolean) {
        editor.putBoolean(KEY_IS_FIRST_OPEN, isFirst).apply()
    }

    fun getIsFirstOpen(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_FIRST_OPEN, false)
    }

    fun clearSharedPreference() {
        editor.clear().apply()
    }

}

