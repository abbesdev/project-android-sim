package com.abbes.schoolspace.adapters.RecycleAdapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R

class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val coursePic : ImageView
    val courseText : TextView


    init {
        coursePic = itemView.findViewById<ImageView>(R.id.imageView3)
        courseText = itemView.findViewById<TextView>(R.id.textDown)
    }

}