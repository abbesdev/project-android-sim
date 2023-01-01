package com.abbes.schoolspace.adminscreeens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.abbes.schoolspace.R
import com.abbes.schoolspace.rest.RestApi
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminScreen : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var totalusersnum : TextView
        lateinit var totalteachersnum : TextView
        lateinit var totalstudentsnum : TextView
        lateinit var totalparentsnum : TextView


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_screen2)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        totalusersnum = findViewById(R.id.totalusersnum)
        totalteachersnum = findViewById(R.id.totalteachersnum)
        totalstudentsnum = findViewById(R.id.totalstudentsnum)

        totalparentsnum = findViewById(R.id.totalparentsnum)


        totalusersnum.text="me"
      ///  textt.Text = "edd"
        /*
        courseName = findViewById(R.id.courseName)

        courseName.text = "Name : $name"*/



/************************************/
        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var intent1 = Intent(applicationContext, TeacherList::class.java)
        var intent2 = Intent(applicationContext, StudentList::class.java)

        var intent3 = Intent(applicationContext, ParentList::class.java)
        var intent4 = Intent(applicationContext, TimeTableScreen::class.java)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.teachers -> startActivity(intent1)
                R.id.students -> startActivity(intent2)
                R.id.parents -> startActivity(intent3)
                R.id.timetables -> startActivity(intent4)

            }
            true
        }

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.22:8080/api/test/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.countUsers()
        retrofitData.enqueue(object : Callback<List<Int>> {
            override fun onResponse(
                call: Call<List<Int>> ,
                response: Response<List<Int>>
            ) {
                val array = response.body()

                totalusersnum.text= array?.get(0).toString()
                totalteachersnum.text= array?.get(1).toString()
                totalstudentsnum.text= array?.get(2).toString()
                totalparentsnum.text= array?.get(3).toString()

                // Toast.makeText(applicationContext, response.code().toString()+" ", Toast.LENGTH_LONG).show()
                if(response.isSuccessful) {



                }
            }


            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                totalusersnum.text="Error"
                totalteachersnum.text="Error"

                totalstudentsnum.text="Error"

                totalparentsnum.text="Error"

            }


        })







    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}