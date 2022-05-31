package com.alex.kotlinmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.alex.kotlinmovies.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    lateinit var AUTH: FirebaseAuth
    lateinit var mBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        CoroutineScope(Dispatchers.Main).launch {
            AUTH = FirebaseAuth.getInstance()
            if (AUTH.currentUser==null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                delay(2000)
                startActivity(Intent(this@SplashActivity, MoviesActivity::class.java))
            }
        }
    }
}


