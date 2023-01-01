package com.abbes.schoolspace.rest

import com.abbes.schoolspace.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RestApi {


    @POST("api/auth/signup")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>



    @POST("api/auth/signin/")
    fun signUser(@Body userData: UserSignIn): Call<UserSignInResponse>



    @GET("subject/getAll")
     fun getAllSubjects(): Call<Subject>


    @GET("classroom/getAll")
    fun getAllClassrooms(): Call<ClassroomResponse>


    @GET("api/test/getuser/{id}")
     fun getCharacter(@Path("id") id: String): Call<UserByIdModel>

    @GET("subject/getOne/{id}")
    fun getSubjectById(@Path("id") id: String): Call<SubjectItem>

    @GET("homework/getAll")
    fun getHomeworkList(): Call<Homework>

    @GET("timetable/getAll")
    fun getAllTimetable(): Call<Timetable>

    @POST("grade/getByStudentAndSubject")
    fun getStudentGradesById(@Body gradeData : GradeVerifyPostRequest): Call<StudentWithGradeByIdModel>

}