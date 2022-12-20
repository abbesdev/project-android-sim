package com.abbes.schoolspace

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class EditInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info)
        val sp1 = getSharedPreferences("Login", MODE_PRIVATE)

        val unm = sp1.getString("Unm", "not added yet")
        val unm1 = sp1.getString("Unm1", "not added yet")
        val unm2 = sp1.getString("Unm3", "not added yet")

        val fullNameHint = findViewById<EditText>(R.id.ee1)
        val hint1 = findViewById<EditText>(R.id.ee2)
        val hint3 = findViewById<EditText>(R.id.ee3)
        val hint4 = findViewById<EditText>(R.id.ee4)

        fullNameHint.setText(unm)
        hint1.setText(unm1)
        hint3.setText(unm2)
        hint4.setText("default value")


    }
}