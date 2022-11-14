package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.models.User

class SignUpScreen: AppCompatActivity() {
    lateinit var editText: EditText

    fun addDummyUser() {
        var fullN = findViewById<EditText>(R.id.testf)
        var email = findViewById<EditText>(R.id.teste)
        var pass = findViewById<EditText>(R.id.testp)
        val apiService = RestApiService()
        val userInfo = User(
            fullname = fullN.text.toString(),
            email = email.text.toString(),
            password = pass.text.toString(),
        )

        apiService.addUser(userInfo) {
            if (it?.fullname != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
            } else {
                val toast = Toast.makeText(applicationContext, "Failed hehe", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)
        var buttonSignup = findViewById<TextView>(R.id.textView2)
var buttonN = findViewById<Button>(R.id.button)
        buttonSignup.setOnClickListener {

            addDummyUser()

        }
        buttonN.setOnClickListener({
            addDummyUser()
        })

    }



}