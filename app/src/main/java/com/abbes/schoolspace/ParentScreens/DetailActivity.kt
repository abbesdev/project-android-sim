package com.abbes.schoolspace.ParentScreens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.ViewPagerAdapter
import com.abbes.schoolspace.models.*
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailActivity : AppCompatActivity() {

    lateinit var courseName : TextView
    lateinit var courseTeacher : TextView
    lateinit var subjectD : TextView

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    var viewPagerAdapter: ViewPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter =  ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
       val imageButtonn : ImageButton = findViewById(R.id.imageButton3)

        imageButtonn.setOnClickListener({
            val intent = Intent(applicationContext, MainScreen::class.java)
            startActivity(intent)
        })

        courseName = findViewById(R.id.titleHead)
        courseTeacher = findViewById(R.id.courseTeacher)
        subjectD = findViewById(R.id.courseTeacher2)


        val picture = intent.getIntExtra(PICTURE,0)


        val name = intent.getStringExtra(NAME)

        val teacher = intent.getStringExtra(TEACHER)
        val subjectt = intent.getStringExtra(SUBJECT)
        val sp1 = getSharedPreferences("Login", MODE_PRIVATE)

        val unmStudent = sp1.getString("Unm3", "not added yet")

        val sp = getSharedPreferences("DetailActivity", AppCompatActivity.MODE_PRIVATE)
        val Ed = sp.edit()

        Ed.putString("mySubject",subjectt )
        Ed.putString("myStudent", unmStudent)
        Ed.commit()

        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)

        val unm = sp1.getString("userid", "not added yet")
        val callSubject: Call<SubjectItem> = api.getSubjectById(subjectt.toString())

        callSubject.enqueue(object : Callback<SubjectItem?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<SubjectItem?>, response: retrofit2.Response<SubjectItem?>) {

                if(response.isSuccessful){


                    if(response.body()?.nameSubject != null) {

                        subjectD.text =
                            "Subject Name :" + response.body()?.nameSubject ?: "No Teacher added yet"
                    }else{
                        subjectD.text = "Subject Name : not assigned yet"
                    }

                }
            }

            override fun onFailure(call: Call<SubjectItem?>, t: Throwable) {
            }

        })


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://project-android-sim.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val call: Call<UserByIdModel> = api.getCharacter(teacher.toString())

        call.enqueue(object : Callback<UserByIdModel?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<UserByIdModel?>, response: retrofit2.Response<UserByIdModel?>) {
                if(response.isSuccessful){

                    if(response.body()?.fullname != null) {

                        courseTeacher.text =
                            "Teacher Name :" + response.body()?.fullname ?: "No Teacher added yet"
                    }else{
                        courseTeacher.text = "Teacher Name : not assigned yet"
                    }

                }
            }

            override fun onFailure(call: Call<UserByIdModel?>, t: Throwable) {
                Toast.makeText(applicationContext, "error hereeee:::", Toast.LENGTH_LONG).show()
            }

        })
        val callParent: Call<UserByIdModel> = api.getCharacter(unm.toString())

        callParent.enqueue(object : Callback<UserByIdModel?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<UserByIdModel?>, response: retrofit2.Response<UserByIdModel?>) {

                if(response.isSuccessful){
                    for(myData in response.body()?.childrens!!) {

                        val call: Call<UserByIdModel> = api.getCharacter(myData.toString())

                        call.enqueue(object : Callback<UserByIdModel?> {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onResponse(call: Call<UserByIdModel?>, response: retrofit2.Response<UserByIdModel?>) {
                                if(response.isSuccessful){

                            }}

                            override fun onFailure(call: Call<UserByIdModel?>, t: Throwable) {
                            }

                        })

                    }


                }
            }

            override fun onFailure(call: Call<UserByIdModel?>, t: Throwable) {
            }

        })



        courseName.text = "$name"
        courseTeacher.text = "Teacher id : $teacher"




    }
}