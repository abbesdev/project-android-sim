package com.abbes.schoolspace.rest

import com.abbes.schoolspace.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


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

    @GET("countusers")
    fun countUsers(): Call<List<Int>>

    @GET("timetable/getAll")
    fun getAllTimetables(): Call<Matiere>

    @GET("classes/getAll")
    fun getAllClasses(): Call<Classe>

    @POST("timetable/post")
    fun createTimetable(@Body Matiere: MatiereInfo): Call<Matiere?>?

    @PATCH("/api/test/confirmation/{id}")
    fun updateUser(@Path("id") id: String): Call<ResponseBody>

    @DELETE("/api/test/deleteuser/{id}")
    fun deleteUser(@Path("id") id: String): Call<ResponseBody>

    @GET("/timetable/getOne/{classe}/{date}")
    fun getClasses(@Path("classe") classe : String,@Path("date") date :String): Call<Matiere>

}