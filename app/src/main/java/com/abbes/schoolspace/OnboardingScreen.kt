package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OnboardingScreen  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen)

        var buttonNext = findViewById<Button>(R.id.button)

        buttonNext.setOnClickListener({
            var intent = Intent(applicationContext, SignInScreen::class.java)
            startActivity(intent)
        })


    }
}