package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.rest.RestApiService
import retrofit2.Response
import java.io.IOException

class SignUpScreen: AppCompatActivity() {
/*

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)
        var buttonSignup = findViewById<TextView>(R.id.textView2)
var buttonN = findViewById<Button>(R.id.button)
        buttonSignup.setOnClickListener {



        }
        buttonN.setOnClickListener({
val fullN = findViewById<EditText>(R.id.testf)
            val email = findViewById<EditText>(R.id.teste)
            val pass = findViewById<EditText>(R.id.testp)
            val apiService = RestApiService()
            val userInfo = UserInfo(fullName = fullN.text.toString(),
            email = email.text.toString(),
            password = pass.text.toString(),
            confirmed = false)

            apiService.addUser(userInfo){

                if(it?.fullName !=null){


                }else{
                    print("not created check code man!!")
                }
                var intent = Intent(applicationContext, NextStepScreen::class.java)
                startActivity(intent)
            }

        })

    }


*/
}