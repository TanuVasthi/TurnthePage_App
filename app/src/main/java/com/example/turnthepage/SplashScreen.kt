package com.example.turnthepage

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.logging.Handler

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        android.os.Handler().postDelayed({
            // Correct way to create an Intent in Kotlin
            val intent = Intent(this, MainActivity::class.java) // Removed ::class.java
            startActivity(intent)
            finish()
        }, 3000)
    }

}