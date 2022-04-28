package com.example.movieapp.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.movieapp.databinding.ActivitySplashBinding
import com.example.movieapp.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Handler(mainLooper).postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }, 2000)
    }
}