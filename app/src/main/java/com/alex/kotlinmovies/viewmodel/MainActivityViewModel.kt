package com.alex.kotlinmovies.viewmodel

import com.alex.kotlinmovies.model.User
import com.alex.kotlinmovies.model.repository.FirebaseRepository
import com.alex.kotlinmovies.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {
    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()
    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}