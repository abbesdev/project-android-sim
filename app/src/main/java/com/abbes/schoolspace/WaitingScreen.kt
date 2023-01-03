package com.abbes.schoolspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WaitingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_screen)
        val btnReturn = findViewById<Button>(R.id.button4)
        btnReturn.setOnClickListener({
            var intent = Intent(applicationContext, SignInScreen::class.java)


            startActivity(intent)
        })


    }
}