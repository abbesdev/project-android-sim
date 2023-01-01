package com.abbes.schoolspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.abbes.schoolspace.adminscreeens.AdminScreen

/*
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            var intent = Intent(applicationContext, OnboardingScreen::class.java)
            startActivity(intent)
        }, 3000)

        }


    }*/
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
         //   var intent = Intent(applicationContext, AdminScreen::class.java)
            var intent = Intent(applicationContext, AdminScreen::class.java)

            startActivity(intent)
        }, 3000)

    }


}
