package com.abbes.schoolspace.rest

import com.abbes.schoolspace.models.Subject
import com.abbes.schoolspace.models.SubjectItem
import retrofit2.Call
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UserSignIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("api/auth/signup")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>


    @Headers("Content-Type: application/json")
    @POST("api/auth/signin")
    fun signUser(@Body userData: UserSignIn): Call<UserSignIn>



    @GET("subject/getAll")
     fun getAllSubjects(): Call<Subject>
}