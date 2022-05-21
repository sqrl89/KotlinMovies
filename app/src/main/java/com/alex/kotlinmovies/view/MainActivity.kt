package com.alex.kotlinmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alex.kotlinmovies.data.User
import com.alex.kotlinmovies.databinding.ActivityMainBinding
import com.alex.kotlinmovies.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    // TODO добавить splash - загрузка, пока идет запрос (корутины)
    // todo single activity + Navigation
    // todo поменять API на кинопоиск??, чтобы пользовать без VPN
    // todo добавить функционал: просмотр трейлера, еще что-нибудь
    // todo уточнить насчет ViewModel - правильно будет наследоваться от ViewModel
    // todo регистрация. нужна ли?
    // todo cache?
    // todo активность с пользователем


    private val mMainActivityViewModel: MainActivityViewModel = MainActivityViewModel()

    private val signInLauncher =
        registerForActivityResult(                                  //создание объекта авторизации экрана
            FirebaseAuthUIActivityResultContract()
        ) { resultCallback ->
            this.onSignInResult(resultCallback)                     // запуск экрана авторизации экрана
        }

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        openRegistrationScreen()

    }

    private fun openRegistrationScreen() {
        startActivity(Intent(this@MainActivity, MoviesActivity::class.java))

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()                                    // интент для экрана firebase auth
        signInLauncher.launch(signInIntent)             // запуск экрана firebase auth
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val authUser = FirebaseAuth.getInstance().currentUser
                authUser?.let {
                    val email = it.email.toString()
                    val uid = it.uid
                    val firebaseUser = User(email, uid)

                    mMainActivityViewModel.updateUserData(firebaseUser, uid)

                    startActivity(Intent(this@MainActivity, MoviesActivity::class.java))
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(
                    this@MainActivity,
                    "Something wrong with registration...",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
            }
        }
    }
}