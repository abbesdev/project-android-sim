package com.abbes.schoolspace.rest

import com.abbes.schoolspace.models.*
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.http.*

interface RestApi {


    @POST("api/auth/signup")
    fun addUser(@Body userData: SignUpWithRole): Call<SignUpWithRole>



    @POST("api/auth/signin/")
    fun signUser(@Body userData: UserSignIn): Call<UserSignInResponse>

    @PATCH("/updatebyidd/{_id}")
    fun updateUserRole(@Path("_id") _id: String): Call<RoleBodyRequestSignup>


    @GET("subject/getAll")
     fun getAllSubjects(): List<Subject>

    @GET("subject/getAll")
    fun getAllSubjectss(): Call<Subject>

   /* @GET("users/getAll")
    fun getAllUsers(): Call<UserInfo>*/
   @GET("/classes/getByClassName/{className}")
   fun getClassByName(@Path("className") className: String): Call<ClassroomResponseByClass>

    @GET("/subject/getBySubjectName/{nameSubject}")
    fun getSubjectByName(@Path("nameSubject") nameSubject: String): Call<SubjectByName>

    @GET("/api/test/getUserByClass/{classes}")
    fun getUsersByClass(@Path("classes") classes: String): Call<ResponseUsersByClassId>


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

    @POST("classroom/post")
    fun createClassroom(@Body Classroom: ClassroomPost): Call<ClassroomPost>

    @PATCH("/api/test/confirmation/{id}")
    fun updateUser(@Path("id") id: String): Call<ResponseBody>

    @DELETE("/api/test/deleteuser/{id}")
    fun deleteUser(@Path("id") id: String): Call<ResponseBody>

    @GET("/timetable/getOne/{classe}/{date}")
    fun getClasses(@Path("classe") classe : String,@Path("date") date :String): Call<Matiere>




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


    @GET("/classroom/getByTeacherId/{id}")
    fun getClassroomByTeacherId(@Path("id") id: String): Call<ClassroomResponse>



    @GET("/api/test/gettimetable/{id}")
    fun getTimetableByParentId(@Path("id") id: String): Call<Matiere>

    @GET("/api/test/gettimetablestudent/{id}")
    fun getTimetableByStudentId(@Path("id") id: String): Call<Matiere>



}