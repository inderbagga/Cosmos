package com.inderbagga.cosmos.utils

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheManager @Inject constructor(@ApplicationContext context : Context){
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getStoredInfo(): String {
        return prefs.getString("INFO", "")!!
    }
    fun setStoredInfo(json: String) {
        prefs.edit().putString("INFO", json).apply()
    }
}