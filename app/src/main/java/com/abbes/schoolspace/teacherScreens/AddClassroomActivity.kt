package com.abbes.schoolspace.teacherScreens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.ParentScreens.MainScreen
import com.abbes.schoolspace.R
import com.abbes.schoolspace.StudentScreens.StudentScreen
import com.abbes.schoolspace.adminscreeens.AdminScreen
import com.abbes.schoolspace.models.*
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AddClassroomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_classroom)
        val title = findViewById<TextView>(R.id.textView15)
         lateinit var classText : String
        lateinit var subjectText : String

        lateinit var arrayAdapter: ArrayAdapter<String>
        var classes: ArrayList<String>
        classes = arrayListOf<String>()
        var subjects: ArrayList<String>
        subjects = arrayListOf<String>()
        lateinit var ListClasses: ArrayList<ClasseItem>
        lateinit var ListSubjects: ArrayList<SubjectItem>
        ListSubjects = arrayListOf<SubjectItem>()

        ListClasses = arrayListOf<ClasseItem>()
        val btnAdd = findViewById<ImageButton>(R.id.imageButton6)

        btnAdd.setOnClickListener({
            val intent = Intent(this, TeacherMainScreen::class.java)
            startActivity(intent)
        })
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        val multiSpinner = findViewById<View>(R.id.spinner5) as Spinner
        val retrofitData = retrofit.getAllClasses()
        retrofitData.enqueue(object : Callback<Classe> {
            override fun onResponse(
                call: Call<Classe>,
                response: retrofit2.Response<Classe>
            ) {
                Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_LONG)
                    .show()

                if (response.isSuccessful) {
                    ListClasses += response.body()!!
                    Log.e("uwu", ListClasses.toString())
                    classes.add("Select a class")
                    for (i in ListClasses.indices) {
                        Log.e("deta", ListClasses[i].nameClasse.toString())
                        classes.add(ListClasses[i].nameClasse)


                        var x = 1

                    }
                    arrayAdapter =
                        ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            classes
                        )
                }

                multiSpinner.adapter = arrayAdapter
            }


            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })


        val multiSpinner1 = findViewById<View>(R.id.spinner4) as Spinner
        val retrofitData1 = retrofit.getAllSubjectss()
        retrofitData1.enqueue(object : Callback<Subject> {
            override fun onResponse(
                call: Call<Subject>,
                response: retrofit2.Response<Subject>
            ) {
                Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_LONG)
                    .show()

                if (response.isSuccessful) {
                    ListSubjects += response.body()!!
                    Log.e("uwu", ListSubjects.toString())
                    subjects.add("Select a subject")
                    for (i in ListSubjects.indices) {
                        Log.e("deta", ListSubjects[i].nameSubject.toString())
                        subjects.add(ListSubjects[i].nameSubject)


                        var x = 1

                    }
                    arrayAdapter =
                        ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_dropdown_item,
                            subjects
                        )
                }

                multiSpinner1.adapter = arrayAdapter
            }


            override fun onFailure(call: Call<Subject>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })

        val btnSubmitCr = findViewById<Button>(R.id.button2)



                    btnSubmitCr.setOnClickListener({

            val preferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
            val unmIdd = preferences.getString("userid", "not added yet")

            lateinit var List1: List<String>
            lateinit var List2: List<String>
                        lateinit var List4: List<String>

            val List3: List<String>
            List3 = arrayListOf<String>(unmIdd.toString())
            val call: Call<ClassroomResponseByClass> = retrofit.getClassByName(multiSpinner.selectedItem.toString())
            val call2: Call<SubjectByName> = retrofit.getSubjectByName(multiSpinner1.selectedItem.toString())

            Toast.makeText(applicationContext, multiSpinner.selectedItem.toString(), Toast.LENGTH_LONG).show()

            call.enqueue(object : Callback<ClassroomResponseByClass?> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<ClassroomResponseByClass?>, response: retrofit2.Response<ClassroomResponseByClass?>) {

                    if(response.isSuccessful){
                        classText = response.body()?.classes.toString()

                        List2 = arrayListOf<String>(classText)
                        val call55: Call<ResponseUsersByClassId> = retrofit.getUsersByClass(classText.toString())



                        call55.enqueue(object : Callback<ResponseUsersByClassId?> {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onResponse(call: Call<ResponseUsersByClassId?>, response: retrofit2.Response<ResponseUsersByClassId?>) {

                                if(response.isSuccessful){



                                    List4 = arrayListOf(response.body()!![0].id)



                                    Log.e("llist",List4.toString())

                                }}

                            override fun onFailure(call: Call<ResponseUsersByClassId?>, t: Throwable) {




                                Log.e("d",response.code().toString())

                            }
                        })
                        call2.enqueue(object : Callback<SubjectByName?> {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onResponse(call: Call<SubjectByName?>, response: retrofit2.Response<SubjectByName?>) {
                                Toast.makeText(applicationContext, response.body()?.subjects.toString(), Toast.LENGTH_LONG).show()

                                if(response.isSuccessful){

                        subjectText = response.body()?.subjects.toString()
                                    List1 = arrayListOf<String>(subjectText)

                        val classroomInfo = ClassroomPost(
                            classroomTitle = title.text.toString(),
                            classes = List2,
                            subject = List1,
                            teacher = List3,
                            students = List4
                        )
                        Log.e("testing cr adding",classroomInfo.toString())


                        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
                        val retrofitData3 = retrofit.createClassroom(classroomInfo)

                        if (retrofitData3 != null) {
                            retrofitData3.enqueue(

                                object : Callback<ClassroomPost> {
                                    override fun onFailure(call: Call<ClassroomPost>, t: Throwable) {


                                    }

                                    override fun onResponse(
                                        call: Call<ClassroomPost>,
                                        response: Response<ClassroomPost>
                                    ) {
                                        Log.e("testing cr ade",response.message().toString())
                                        Log.e("testing cr ade",response.errorBody().toString())

                                        Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_LONG).show()
                                        val addedUser = response.body()

                                        if (response.code() == 200) {
                                            val intent = Intent(applicationContext, TeacherMainScreen::class.java)
                                            startActivity(intent)
                                        } else {

                                        }


                                    }

                                })}


                    }}

                            override fun onFailure(call: Call<SubjectByName?>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })

                    }

                }

                override fun onFailure(call: Call<ClassroomResponseByClass?>, t: Throwable) {
                    Toast.makeText(applicationContext, "error hereeee:::", Toast.LENGTH_LONG).show()
                }


            })



        })}




}

