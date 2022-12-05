package com.abbes.schoolspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

import com.abbes.schoolspace.adapters.RecycleAdapter.ViewPagerAdapter
import com.abbes.schoolspace.adapters.StudentAdapter
import com.abbes.schoolspace.fragments.notverified
import com.abbes.schoolspace.fragments.verified
import com.abbes.schoolspace.models.UserInfo
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class StudentList : AppCompatActivity() {
    private lateinit var newRecylerview : RecyclerView
    //private lateinit var viewPager: ViewPager
    private lateinit var viewPager : ViewPager
    private lateinit var tabs: TabLayout

    private lateinit var newArrayList : ArrayList<UserInfo>


    lateinit var fullname : Array<String>
    lateinit var email : Array<String>
    lateinit var confirmed : Array<Boolean>
    lateinit var password : Array<String>



    lateinit var toggle : ActionBarDrawerToggle






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        fullname = arrayOf("Beyram ayadi","Mohammed Abbes")
        email =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        password =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        confirmed = arrayOf(true,false)
        setUpTabs()

        newRecylerview =findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)


        newArrayList = arrayListOf<UserInfo>()
        getUserdata()




        /****menu***/


        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var intent1 = Intent(applicationContext, TeacherList::class.java)
        var intent2 = Intent(applicationContext, StudentList::class.java)

        var intent3 = Intent(applicationContext, ParentList::class.java)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.teachers -> startActivity(intent1)
                R.id.students -> startActivity(intent2)
                R.id.parents -> startActivity(intent3)
            }
            true
        }




    }

    private fun setUpTabs(){

        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.tabs)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(verified(),"Verified")
        adapter.addFragment(notverified(),"Not verified")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }
    private fun getUserdata() {

        for(i in fullname.indices){

            val student = UserInfo(fullname[i],email[i], password[i],confirmed[i])
            newArrayList.add(student)

        }
        val adapter = StudentAdapter(newArrayList)
        newRecylerview.adapter = adapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}

