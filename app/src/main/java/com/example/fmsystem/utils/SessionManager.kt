package com.example.fmsystem.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("FM_SESSION", Context.MODE_PRIVATE)

    fun saveLoginStatus(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }

    fun saveProfileStatus(isCompleted: Boolean) {
        prefs.edit().putBoolean("profileCompleted", isCompleted).apply()
    }

    fun isProfileCompleted(): Boolean {
        return prefs.getBoolean("profileCompleted", false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}