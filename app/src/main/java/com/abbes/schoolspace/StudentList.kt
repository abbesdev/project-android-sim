package com.abbes.schoolspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.adapters.StudentAdapter
import com.abbes.schoolspace.models.UserInfo

class StudentList : AppCompatActivity() {
    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<UserInfo>

    lateinit var fullname : Array<String>
    lateinit var email : Array<String>
    lateinit var confirmed : Array<Boolean>
    lateinit var password : Array<String>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        fullname = arrayOf("Beyram ayadi","Mohammed Abbes")
        email =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        password =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        confirmed = arrayOf(true,false)

        newRecylerview =findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)

        newArrayList = arrayListOf<UserInfo>()
        getUserdata()


    }
    private fun getUserdata() {

        for(i in fullname.indices){

            val student = UserInfo(fullname[i],email[i], password[i],confirmed[i])
            newArrayList.add(student)

        }
        val adapter = StudentAdapter(newArrayList)
        newRecylerview.adapter = adapter

    }

}