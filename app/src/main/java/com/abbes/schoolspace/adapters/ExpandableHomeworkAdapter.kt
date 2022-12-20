package com.abbes.schoolspace.adapters

import android.R
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import com.abbes.schoolspace.models.HomeworkItem


class ExpandableHomeworkAdapter(

    private val context: Context,
    private val imageModelArrayList: ArrayList<HomeworkItem>,
    private val expandableListDetail: HashMap<HomeworkItem, ArrayList<HomeworkItem>>


) : BaseExpandableListAdapter() {
    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
      return 0
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View, parent: ViewGroup
    ): View {

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return listPosition;
    }

    override fun getGroup(listPosition: Int): Any {
       // return expandableListTitle[listPosition]
        return 0
    }

    override fun getGroupCount(): Int {
        return 0
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View, parent: ViewGroup
    ): View {
        var convertView = convertView
        val holder: ExpandableHomeworkAdapter.ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(com.abbes.schoolspace.R.layout.course_item, null, true)

            if (convertView != null) {
                holder.tvname = convertView.findViewById(com.abbes.schoolspace.R.id.name) as TextView
            }
            if (convertView != null) {
                holder.iv = convertView.findViewById(com.abbes.schoolspace.R.id.imgView) as ImageView
            }

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ExpandableHomeworkAdapter.ViewHolder
        }

        holder.tvname!!.setText(imageModelArrayList[listPosition].homeworkTitle)
        holder.iv!!.setImageResource(com.abbes.schoolspace.R.drawable.todo)

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
    private inner class ViewHolder {

        var tvname: TextView? = null
        internal var iv: ImageView? = null

    }
}