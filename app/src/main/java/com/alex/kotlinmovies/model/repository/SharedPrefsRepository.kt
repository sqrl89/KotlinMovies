package com.alex.kotlinmovies.model.repository

interface SharedPrefsRepository {
    fun saveInPrefs(value: String): String
}