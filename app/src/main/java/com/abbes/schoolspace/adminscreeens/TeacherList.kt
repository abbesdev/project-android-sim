package com.abbes.schoolspace.adminscreeens

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.abbes.schoolspace.R
import com.abbes.schoolspace.SignInScreen
import com.abbes.schoolspace.adapters.RecycleAdapter.ViewPagerAdapter
import com.abbes.schoolspace.adapters.UserAdapter
import com.abbes.schoolspace.fragments.notverifiedteacher
import com.abbes.schoolspace.fragments.verifiedteacher
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UsersInf
import com.abbes.schoolspace.models.UsersInfItem
import com.abbes.schoolspace.rest.RestApi
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeacherList : AppCompatActivity() {


    private lateinit var newRecylerview : RecyclerView
    //private lateinit var viewPager: ViewPager
    private lateinit var viewPager : ViewPager
    private lateinit var tabs: TabLayout

    private lateinit var newArrayList : ArrayList<UserInfo>
    private lateinit var newArrayList2 : ArrayList<UsersInfItem>



    lateinit var fullname : Array<String>
    lateinit var email : Array<String>
    lateinit var confirmed : Array<Boolean>
    lateinit var password : Array<String>

    lateinit var toggle : ActionBarDrawerToggle








    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_list)
        fullname = arrayOf("Beyram ayadi","Mohammed Abbes")
        email =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        password =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        confirmed = arrayOf(true,false)
        val drawerLayout1 : DrawerLayout = findViewById(R.id.drawerLayout)

        val toggButton = findViewById<ImageButton>(R.id.imageButton3)

        toggButton.setOnClickListener({
            drawerLayout1.open()
        })

        setUpTabs()

        newRecylerview =findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)
        newArrayList = arrayListOf<UserInfo>()
       newArrayList2 = arrayListOf<UsersInfItem>()
        getUsers()



       // getUserdata()
        getUsers()


        /****menu***/

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

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
                R.id.paiements -> {
                    val preferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
                    if (preferences != null) {
                        preferences.edit().clear().commit()
                        val intent = Intent (this, SignInScreen::class.java)
                        startActivity(intent)
                    }
                }
            }
            true
        }




    }



    private fun getUsers(){
        newArrayList2 = arrayListOf<UsersInfItem>()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://172.16.5.48:8080/api/test/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getAllUser()
        retrofitData.enqueue(object : Callback<UsersInf> {
            override fun onResponse(
                call: Call<UsersInf>,
                response: retrofit2.Response<UsersInf>
            ) {
               // Log.e("kbsk",response.code().toString())


                //Toast.makeText(applicationContext, response.code().toString()+" ",Toast.LENGTH_LONG).show()
                if(response.isSuccessful){
                     newArrayList2 = response.body()!!
                    Log.e("kbsk",response.body().toString())
                    val adapter = UserAdapter(newArrayList2)
                    newRecylerview.adapter = adapter

                }


/*  else
                   Toast.makeText(applicationContext, "error",Toast.LENGTH_LONG).show()*/


              /*  for(UserX in response.body()!!){
                    newArrayList2.add(UserX)
                }*/


                //Toast.makeText(applicationContext, "No Errors",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<UsersInf?>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
              //  Log.e("errr",t.toString())

            }
        })

    }

    private fun setUpTabs(){

        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.tabs)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(verifiedteacher(),"Verified")
        adapter.addFragment(notverifiedteacher(),"Not verified")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }
    /*
    private fun getUserdata() {

        for(i in fullname.indices){

            val student = UserInfo(fullname[i],email[i], password[i],confirmed[i])
            newArrayList.add(student)

        }
        val adapter = UserAdapter(newArrayList2)
        newRecylerview.adapter = adapter

    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}