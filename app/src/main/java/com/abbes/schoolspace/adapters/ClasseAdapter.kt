package com.abbes.schoolspace.adapters

import android.app.TimePickerDialog
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.models.MatiereInfo
import org.threeten.bp.ZonedDateTime.ofInstant
import java.time.LocalDate
import java.time.LocalDateTime.ofInstant
import java.time.LocalTime
import java.time.LocalTime.ofInstant
import java.time.OffsetTime.ofInstant
import java.time.ZoneId

class ClasseAdapter(private val matiereList : ArrayList<MatiereInfo>, private val selectedDate : LocalDate): RecyclerView.Adapter<ClasseAdapter.MyViewHolder>() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ClasseAdapter.MyViewHolder, position: Int) {
        val currentItem = matiereList[position]
       // val date : LocalDate
 /*       val start : LocalTime
        val end : LocalTime*/

        //  date  = LocalDate.ofInstant(currentItem.timestart.toInstant(), ZoneId.systemDefault())

        val date : LocalDate = currentItem.timestart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
      var  start : String = currentItem.timestart.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString()
       var end : String = currentItem.timeend.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString()




        var t1 = currentItem.timestart.toString()
        var t2 = currentItem.timeend.toString()
        var t3 = start + " - " + end
        if(date == selectedDate) {

            holder.classe.text = currentItem.test
            holder.time.text = t3
        }





    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasseAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_detail_table,
            parent,false)


        return ClasseAdapter.MyViewHolder(itemView)


    }
    override fun getItemCount(): Int {
        return matiereList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val classe: TextView = itemView.findViewById(R.id.classe)
        val time: TextView = itemView.findViewById(R.id.time)
    }

}