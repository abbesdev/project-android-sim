package com.abbes.schoolspace.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.ParentScreens.DetailActivity
import com.abbes.schoolspace.R
import com.abbes.schoolspace.models.NAME
import com.abbes.schoolspace.models.PICTURE
import com.abbes.schoolspace.models.SubjectItem
import com.abbes.schoolspace.models.TimetableItem
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


class RecycleAdapterTimetable(val courseList: ArrayList<TimetableItem>, val context: Context?):RecyclerView.Adapter<RecycleAdapterTimetable.ViewHolder>() {

class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

val subjectText:TextView= itemView.findViewById(R.id.textDown)
    val subjectImage:ImageView= itemView.findViewById(R.id.imageView3)}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exam_item, parent, false)

        return ViewHolder(view)
    }
    private fun  getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val aURL = URL(url)
            val conn: URLConnection = aURL.openConnection()
             conn.connect()
            val `is`: InputStream = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error getting bitmap", e)
        }
        return bm
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = courseList[position].title

        holder.subjectText.setText(courseList.get(position).title);



    }

    override fun getItemCount() = courseList.size


}
