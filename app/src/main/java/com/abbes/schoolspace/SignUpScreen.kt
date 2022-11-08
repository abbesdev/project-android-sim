package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignUpScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)
        var buttonSignup = findViewById<TextView>(R.id.textView2)
var buttonN = findViewById<Button>(R.id.button)
        buttonSignup.setOnClickListener({
            var intent = Intent(applicationContext, SignInScreen::class.java)
            startActivity(intent)
        })
        buttonN.setOnClickListener({
            var intent2 = Intent(applicationContext, NextStepScreen::class.java)
            startActivity(intent2)
        })

    }
}