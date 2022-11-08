package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignInScreen  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_screen)
        var buttonSignup = findViewById<TextView>(R.id.textView2)

        buttonSignup.setOnClickListener({
            var intent = Intent(applicationContext, SignUpScreen::class.java)
            startActivity(intent)
        })

    }
}