package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UserSignIn
import com.abbes.schoolspace.rest.RestApiService

class SignInScreen  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_screen)
        var buttonSignup = findViewById<TextView>(R.id.textView2)
var buttonLogin = findViewById<Button>(R.id.button)
        val email = findViewById<EditText>(R.id.androidemail)
        val pass = findViewById<EditText>(R.id.androidpass)
        buttonLogin.setOnClickListener({
            val apiService = RestApiService()
            val userInfo = UserSignIn(
                email = email.text.toString(),
                password = pass.text.toString())

            apiService.signUser(userInfo){

                var intent = Intent(applicationContext, MainScreen::class.java)
                startActivity(intent)
            }

        })
        buttonSignup.setOnClickListener({
            var intent = Intent(applicationContext, SignUpScreen::class.java)
            startActivity(intent)
        })

    }
}