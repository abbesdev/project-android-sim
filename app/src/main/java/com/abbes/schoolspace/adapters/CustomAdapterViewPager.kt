package com.abbes.schoolspace.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.abbes.schoolspace.ParentScreens.DetailActivity
import com.abbes.schoolspace.R
import com.abbes.schoolspace.models.*
import kotlin.collections.ArrayList

class CustomAdapterViewPager(private val context: Context, private val imageModelArrayList: ArrayList<StudentWithGradeByIdModelItem>) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return imageModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.course_item, null, true)

            if (convertView != null) {
                holder.tvname = convertView.findViewById(R.id.name) as TextView
            }
            if (convertView != null) {
                holder.iv = convertView.findViewById(R.id.imgView) as ImageView
            }

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvname!!.setText(imageModelArrayList[position].gradeName + " : "+imageModelArrayList[position].gradeValue)
        holder.iv!!.setImageResource(R.drawable.high_school_physics_120415_large)

        return convertView
    }

    private inner class ViewHolder {

        var tvname: TextView? = null
        internal var iv: ImageView? = null

    }

}