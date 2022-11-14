package com.abbes.schoolspace

import com.abbes.schoolspace.models.User
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RestApiService {
    fun addUser(userData: User, onResult: (User?) -> Unit){
        val retrofit = Client.buildService(ApiService::class.java)
        retrofit.addUser(userData).enqueue(
            object : retrofit2.Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<User>, response: Response<User>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}