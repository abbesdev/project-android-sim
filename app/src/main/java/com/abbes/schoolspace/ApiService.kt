package com.abbes.schoolspace


import com.abbes.schoolspace.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: User): Call<User>
}