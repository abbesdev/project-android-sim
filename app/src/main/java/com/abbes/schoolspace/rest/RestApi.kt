package com.abbes.schoolspace.rest

import com.abbes.schoolspace.models.Subject
import retrofit2.Call
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UserSignIn
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {


    @POST("api/auth/signup")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>



    @POST("api/auth/signin/")
    fun signUser(@Body userData: UserSignIn): Call<UserSignInResponse>



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