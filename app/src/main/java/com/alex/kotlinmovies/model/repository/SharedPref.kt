package com.alex.kotlinmovies.model.repository

import android.content.Context
import android.preference.PreferenceManager

@Suppress("DEPRECATION")
class SharedPref {
    companion object{
        fun setFavorite(context: Context, key: String, value: Boolean){
            val setFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
             setFavoriteShared.edit().putBoolean(key, value).apply()
        }
        fun getFavorite(context: Context, key: String): Boolean{
            val setFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            return setFavoriteShared.getBoolean(key, false)
        }
    }
}