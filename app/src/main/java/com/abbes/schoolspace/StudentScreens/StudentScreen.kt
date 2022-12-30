package com.abbes.schoolspace.StudentScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.abbes.schoolspace.ParentScreens.CalendarFragment
import com.abbes.schoolspace.ParentScreens.HomeFragment
import com.abbes.schoolspace.ParentScreens.ProfileFragment
import com.abbes.schoolspace.ParentScreens.ReportFragment
import com.abbes.schoolspace.R
import com.abbes.schoolspace.StudentScreens.Fragments.CalendarStudentFragment
import com.abbes.schoolspace.StudentScreens.Fragments.HomeStudentFragment
import com.abbes.schoolspace.StudentScreens.Fragments.ProfileStudentFragment
import com.abbes.schoolspace.StudentScreens.Fragments.ReportStudentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudentScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_screen)
        val firstFragment= HomeStudentFragment()
        val secondFragment= ReportStudentFragment()
        val thirdFragment= CalendarStudentFragment()
        val fourthFragment= ProfileStudentFragment()
        setCurrentFragment(firstFragment)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView1)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->setCurrentFragment(firstFragment)
                R.id.book ->setCurrentFragment(secondFragment)
                R.id.calendar ->setCurrentFragment(thirdFragment)
                R.id.person ->setCurrentFragment(fourthFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment1,fragment)
            commit()
        }
}