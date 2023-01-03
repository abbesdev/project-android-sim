package com.abbes.schoolspace.rest

import android.content.Context
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.abbes.schoolspace.models.*
import java.security.AccessController.getContext


class RestApiService {


    fun addUser(userData: SignUpWithRole, onResult: (SignUpWithRole?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(

        object :Callback<SignUpWithRole> {
           override fun onFailure(call: Call<SignUpWithRole>, t:Throwable){
               onResult(null)
           }

            override fun onResponse(call: Call<SignUpWithRole>, response: Response<SignUpWithRole>) {
                val addedUser = response.body()
                if(response.isSuccessful){
                    val s :String
                    s = response.body().toString()




                }
                onResult(addedUser)
            }

        })
    }

}