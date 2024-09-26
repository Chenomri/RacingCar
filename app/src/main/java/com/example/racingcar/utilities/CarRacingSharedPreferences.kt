package com.example.racingcar.utilities

import android.content.Context
import android.content.SharedPreferences

class CarRacingSharedPreferences private constructor(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(Constants.DATA_KEY, Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: CarRacingSharedPreferences? = null

        fun init(context: Context): CarRacingSharedPreferences {
            return instance ?: synchronized(this) {
                instance ?: CarRacingSharedPreferences(context).also { instance = it }
            }
        }

        fun getInstance(): CarRacingSharedPreferences {
            return instance
                ?: throw IllegalStateException("SharedPreferencesManagerV3 must be initialized by calling init(context) before use.")
        }
    }

    fun putString(key: String, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }
}