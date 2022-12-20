package com.abbes.schoolspace

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.ParentScreens.MainScreen
import com.abbes.schoolspace.models.UserSignIn
import com.abbes.schoolspace.models.UserSignInResponse
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInScreen  : AppCompatActivity() {

    private fun doLogin(){
        val email = findViewById<EditText>(R.id.androidemail)
        val pass = findViewById<EditText>(R.id.androidpass)
        val remmeberme = findViewById<CheckBox>(R.id.checkBox)

        val userInfo =  UserSignIn(
            email = email.text.toString(),
            password = pass.text.toString()
        )

        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.signUser(userInfo).enqueue(

            object : Callback<UserSignInResponse> {
                override fun onFailure(call: Call<UserSignInResponse>, t: Throwable) {
                    Toast.makeText(this@SignInScreen,"errorr", Toast.LENGTH_LONG).show()


                }

                override fun onResponse(
                    call: Call<UserSignInResponse>,
                    response: Response<UserSignInResponse>
                ) {
                    val addedUser = response.body()

                    if(response.code() == 200){
                        Toast.makeText(this@SignInScreen,"Login success $addedUser", Toast.LENGTH_LONG).show()
                        if (addedUser != null) {
                            if(addedUser.roles.contains("ROLE_PARENT")){
                                var intent = Intent(applicationContext, MainScreen::class.java)
                                if (addedUser != null && remmeberme.isChecked) {
                                    intent.putExtra("fullname",addedUser.fullname.toString())
                                    intent.putExtra("numChild",addedUser.childrens.size.toString())
                                    val sp = getSharedPreferences("Login", MODE_PRIVATE)
                                    val Ed = sp.edit()
                                    Ed.putString("Unm", addedUser.fullname)
                                    Ed.putString("userid",addedUser.id)
                                    Ed.putString("Unm1", addedUser.email)
                                    Ed.putString("Unm2", addedUser.childrens.count().toString())
                                    Ed.putString("Unm3", addedUser.childrens.first().toString())

                                    Ed.commit()
                                }
                                startActivity(intent)
                            }
                            else
                            {
                                    Toast.makeText(this@SignInScreen,"Role NOT permitted ${addedUser.roles}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }else{

                        Toast.makeText(this@SignInScreen,"Error your credentials are incorrect my boi", Toast.LENGTH_LONG).show()
                    }


                }

            })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_screen)
       val btnLogin : Button
       btnLogin = findViewById(R.id.button)

        var buttonSignup = findViewById<TextView>(R.id.textView2)



  btnLogin.setOnClickListener({
doLogin()
  })
        buttonSignup.setOnClickListener({
            var intent = Intent(applicationContext, SignUpScreen::class.java)
            startActivity(intent)
        })

    }
}