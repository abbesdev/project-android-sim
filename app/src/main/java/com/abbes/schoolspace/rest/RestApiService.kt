package com.abbes.schoolspace.rest

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.abbes.schoolspace.models.Subject
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UserSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.abbes.schoolspace.models.UserSignInResponse
import java.security.AccessController.getContext


class RestApiService {


    fun addUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(

        object :Callback<UserInfo> {
           override fun onFailure(call: Call<UserInfo>, t:Throwable){
               onResult(null)
           }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val addedUser = response.body()
                if(response.isSuccessful){
                    val s :String
                    s = response.body().toString()




                }
                onResult(addedUser)
            }

        })
    }
    fun signUser(userData: UserSignIn, onResult: (UserSignIn?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.signUser(userData).enqueue(

            object :Callback<UserSignIn> {
                override fun onFailure(call: Call<UserSignIn>, t:Throwable){
                    onResult(null)
                }

                override fun onResponse(call: Call<UserSignIn>, response: Response<UserSignIn>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }

            })
    }
}