package com.abbes.schoolspace.rest

import com.abbes.schoolspace.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Response


interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("api/auth/signup")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>


    @Headers("Content-Type: application/json")
    @POST("api/auth/signin")
    fun signUser(@Body userData: UserSignIn): Call<UserSignIn>



    @GET("subject/getAll")
     fun getAllSubjects(): List<Subject>



   /* @GET("users/getAll")
    fun getAllUsers(): Call<UserInfo>*/


    @GET("getallusers")
    fun getAllUser(): Call<UsersInf>

}