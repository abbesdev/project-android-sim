package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.ParentScreens.MainScreen
import com.abbes.schoolspace.models.RoleBodyRequestSignup
import com.abbes.schoolspace.models.SignUpWithRole
import com.abbes.schoolspace.models.UserSignUpInfo
import com.abbes.schoolspace.rest.RestApiService

class NextStepScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_step_screen)
        var buttonSignup = findViewById<TextView>(R.id.buttonContinue)

        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        val spinner2: Spinner = findViewById(R.id.spinner2)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.classes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }

        buttonSignup.setOnClickListener({


            val apiService = RestApiService()
            val userInfo = SignUpWithRole(
                fullname = intent.getStringExtra("fn").toString(),
                email =  intent.getStringExtra("mail").toString(),
                password =  intent.getStringExtra("pwd").toString(),
                roles = arrayListOf(spinner.selectedItem.toString()),

                )

            apiService.addUser(userInfo){

if (it != null) {
    var intent = Intent(applicationContext, WaitingScreen::class.java)
    startActivity(intent)

}

            }

        })
    }

}