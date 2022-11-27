package com.abbes.schoolspace

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.models.NAME
import com.abbes.schoolspace.models.PICTURE

class DetailActivity : AppCompatActivity() {

    lateinit var coursePic : ImageView
    lateinit var courseName : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        coursePic = findViewById(R.id.coursePic)
        courseName = findViewById(R.id.courseName)


        coursePic.setImageResource(intent.getIntExtra(PICTURE, 0))

        val name = intent.getStringExtra(NAME)
        val picture = intent.getStringExtra(PICTURE)


        title= "$name Detail"

        courseName.text = "Name : $name"


    }
}