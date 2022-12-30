package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.ParentScreens.MainScreen
import com.abbes.schoolspace.StudentScreens.StudentScreen

class OnboardingScreen  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen)

        var buttonNext = findViewById<Button>(R.id.button)

        buttonNext.setOnClickListener({
            val sp1 = getSharedPreferences("Login", MODE_PRIVATE)

            val unm = sp1.getString("Unm","def")
            val unmRole = sp1.getString("roletype","def")


            if(unm == "def"){
            var intent = Intent(applicationContext, SignInScreen::class.java)
            startActivity(intent)}
            else if (unmRole == "ROLE_PARENT"){
                var intent = Intent(applicationContext, MainScreen::class.java)
                startActivity(intent)}
           else if (unmRole == "ROLE_STUDENT"){
                var intent = Intent(applicationContext, StudentScreen::class.java)
                startActivity(intent)}else { var intent = Intent(applicationContext, SignInScreen::class.java)
                startActivity(intent)}
        })


    }
}