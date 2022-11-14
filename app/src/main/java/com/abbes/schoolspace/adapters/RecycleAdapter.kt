package com.abbes.schoolspace.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.DetailActivity
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.RecycleAdapter.CourseViewHolder
import com.abbes.schoolspace.models.Course
import com.abbes.schoolspace.models.NAME
import com.abbes.schoolspace.models.PICTURE

class CourseAdapter(val courseList: MutableList<Course>) : RecyclerView.Adapter<CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exam_item, parent, false)

        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        val name = courseList[position].courseName


        holder.coursePic.setImageResource(courseList[position].coursePic)
        holder.courseText.text = name


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.apply {
                putExtra(PICTURE, courseList[position].coursePic);
                putExtra(NAME, name);

            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = courseList.size

}
