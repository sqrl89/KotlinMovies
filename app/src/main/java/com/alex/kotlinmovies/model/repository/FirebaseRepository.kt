package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.model.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}