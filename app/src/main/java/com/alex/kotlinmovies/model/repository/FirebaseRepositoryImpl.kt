package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {

    private var database: DatabaseReference =
        Firebase.database("https://kotlinmovie-55eaf-default-rtdb.asia-southeast1.firebasedatabase.app").reference // объект дял записи в БД

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser) // сохраняет пользователя в Firebase Realtime
    }
}