package com.abbes.schoolspace.teacherScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.abbes.schoolspace.R
import com.abbes.schoolspace.StudentScreens.Fragments.CalendarStudentFragment
import com.abbes.schoolspace.StudentScreens.Fragments.HomeStudentFragment
import com.abbes.schoolspace.StudentScreens.Fragments.ProfileStudentFragment
import com.abbes.schoolspace.StudentScreens.Fragments.ReportStudentFragment
import com.abbes.schoolspace.teacherScreens.Fragments.CalendarFragmentTeacher
import com.abbes.schoolspace.teacherScreens.Fragments.ClassroomFragmentTeacher
import com.abbes.schoolspace.teacherScreens.Fragments.HomeFragmentTeacher
import com.abbes.schoolspace.teacherScreens.Fragments.ProfileFragmentTeacher
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeacherMainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_main_screen)
        val firstFragment= HomeFragmentTeacher()
        val secondFragment= ClassroomFragmentTeacher()
        val thirdFragment= CalendarFragmentTeacher()
        val fourthFragment= ProfileFragmentTeacher()
        setCurrentFragment(firstFragment)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)

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
            replace(R.id.flFragment2,fragment)
            commit()
        }
}