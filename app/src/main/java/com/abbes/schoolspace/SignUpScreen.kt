package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UserSignUpInfo
import com.abbes.schoolspace.rest.RestApiService
import com.abbes.schoolspace.teacherScreens.TeacherMainScreen
import retrofit2.Response
import java.io.IOException

class SignUpScreen: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)
        var buttonSignup = findViewById<TextView>(R.id.textView2)
var buttonN = findViewById<Button>(R.id.button)
        buttonSignup.setOnClickListener {

            val intent = Intent(this, SignInScreen::class.java)
            startActivity(intent)

        }
        buttonN.setOnClickListener({
val fullN = findViewById<EditText>(R.id.testf)
            val email = findViewById<EditText>(R.id.teste)
            val pass = findViewById<EditText>(R.id.testp)

                var intent = Intent(applicationContext, NextStepScreen::class.java)
            intent.putExtra("fn", fullN.text.toString())
            intent.putExtra("mail", email.text.toString())
            intent.putExtra("pwd", pass.text.toString())

            startActivity(intent)


        })

    }



}