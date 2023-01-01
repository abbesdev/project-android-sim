package com.abbes.schoolspace.adminscreeens

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.abbes.schoolspace.R
import com.abbes.schoolspace.fragments.BottomSheetFragment
import com.abbes.schoolspace.models.Classe
import com.abbes.schoolspace.models.ClasseItem
import com.abbes.schoolspace.models.Matiere
import com.abbes.schoolspace.models.MatiereInfo
import com.abbes.schoolspace.rest.RestApi
import com.framgia.library.calendardayview.CalendarDayView
import com.framgia.library.calendardayview.data.IEvent
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import java.time.LocalDate
import java.time.format.DateTimeFormatter



class TimeTableDetailsScreen : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var toggle : ActionBarDrawerToggle


//TimePickerDialog.OnTimeSetListener


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_details_screen)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        var dayView: CalendarDayView = findViewById(R.id.dayView)
        var day : TextView = findViewById(R.id.day2)
        dayView.setLimitTime(0, 23)

        lateinit var ListClasses : ArrayList<ClasseItem>
        ListClasses = arrayListOf<ClasseItem>()

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var intent1 = Intent(applicationContext, TeacherList::class.java)
        var intent2 = Intent(applicationContext, StudentList::class.java)

        var intent3 = Intent(applicationContext, ParentList::class.java)
        var intent4 = Intent(applicationContext, TimeTableScreen::class.java)



        /********************sidemenu**************/

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.teachers -> startActivity(intent1)
                R.id.students -> startActivity(intent2)
                R.id.parents -> startActivity(intent3)
                R.id.timetables -> startActivity(intent4)
            }
            true
        }
        /****************************************************/
        val addsubject = findViewById<TextView>(R.id.add_subject)

/*
        addsubject.setOnClickListener{
            @Override
            public void
        }*/
        val bottomSheetFragment = BottomSheetFragment()

        addsubject.setOnClickListener{
            bottomSheetFragment.show(supportFragmentManager,"BottomSheetDialog")
        }




        var classes : ArrayList<String>
     classes =    arrayListOf<String>()
        Log.e("classin",classes.toString())
        Log.e("classin","11")
        var x : Int = 0


        val spinner = findViewById<Spinner>(R.id.spinner3)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,classes)
       // spinner.

        /********************  Get Classes list ***********************************************/
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.22:8080/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getAllClasses()
        retrofitData.enqueue(object : Callback<Classe> {
            override fun onResponse(
                call: Call<Classe>,
                response: retrofit2.Response<Classe>
            ) {
                if(response.isSuccessful){
                    ListClasses += response.body()!!
                    Log.e("uwu",ListClasses.toString())
                    for(i in ListClasses.indices){
                        Log.e("deta",ListClasses[i].nameClasse.toString())
                       classes.add(ListClasses[i].nameClasse)

                  //      classes += ListClasses[i].nameClasse
                        Log.e("details",classes.toString())
                         x = 1

                    }
                    Log.e("classin",classes.toString())

                    spinner.adapter = arrayAdapter
                    /********************* ******************************/




                    /***************************************************/

                }
            }

            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })

/******************************Date picker***********************************************/

        day.setOnClickListener {
            // Do something when the button is clicked
            //     Toast.makeText(context, "button works",Toast.LENGTH_LONG).show()
            /* getDateTimeCalendar()
             TimePickerDialog(context,this,hour,minute,true).show()*/
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                // Use the selected year, month, and day to update a TextView or do something else
                var dayofmon =""
                var mont =""
                val month2 = month +1

                if(dayOfMonth<10)
                    dayofmon = "0" + dayOfMonth.toString()
                if(dayOfMonth>9)
                    dayofmon = dayOfMonth.toString()
                if(month2<10)
                    mont  = "0"+month2.toString()
                if(month2>9)
                    mont = month2.toString()

             //   day.text = dayofmon + "-" + mont+ "-" + year.toString()

                day.text =  year.toString()+"-" + mont + "-" + dayofmon

              /*  val dateprov = day.text.toString()
                val date = LocalDate.parse(dateprov, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                Log.e("uwu2",date.toString())*/

                var dateprov = day.text.toString()
                lateinit var newArrayList : ArrayList<MatiereInfo>


                newArrayList = arrayListOf<MatiereInfo>()

                var selectedItem = spinner.selectedItem.toString()
                Log.e("first",dateprov +" --- "+selectedItem)
                val retrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://192.168.1.22:8080/")
                    .build()
                    .create(RestApi::class.java)
                val retrofitData = retrofitBuilder.getClasses(selectedItem,dateprov)
                retrofitData.enqueue(object : Callback<Matiere> {
                    override fun onResponse(
                        call: Call<Matiere>,
                        response: retrofit2.Response<Matiere>
                    ) {
                        if(response.isSuccessful){
                            newArrayList = response.body()!!
                            Log.e("response",response.body().toString())

                            var events: ArrayList<IEvent?>?

                            events = ArrayList()

                            for(i in newArrayList.indices){

                                val calendar = Calendar.getInstance()
                                calendar.time = newArrayList[i].timestart
                                val calendar2 = Calendar.getInstance()
                                calendar2.time = newArrayList[i].timeend


                                var hour = calendar.get(Calendar.HOUR_OF_DAY)
                                val minute = calendar.get(Calendar.MINUTE)
                                var hour2 = calendar2.get(Calendar.HOUR_OF_DAY)
                                val minute2 = calendar2.get(Calendar.MINUTE)
                             //   val eventColor : ContextCompat
                                var color = R.color.bluemain

                                when(newArrayList[i].title) {
                                    "Math" -> color = R.color.bluemain
                                    "Francais" ->  color = R.color.red
                                    "Technique" ->  color = R.color.orange
                                    "SVT" ->  color = R.color.green


                                }

                               val eventColor = ContextCompat.getColor(applicationContext,color)
                                val timeStart = Calendar.getInstance()
                                val timeEnd = timeStart.clone() as Calendar

                                timeStart.set(Calendar.HOUR_OF_DAY, hour)
                                timeStart.set(Calendar.MINUTE, minute)
                                timeEnd.set(Calendar.HOUR_OF_DAY, hour2)
                                timeEnd.set(Calendar.MINUTE, minute2)


                                val event = Event(1, timeStart, timeEnd, newArrayList[i].title, "Hockaido", eventColor)
                                events!!.add(event);
                            }











                            Log.e("calendar", "you are here")




                            dayView.setEvents(events);







                        }
                    }

                    override fun onFailure(call: Call<Matiere>, t: Throwable) {
                        Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                        Log.e("failure",t.toString())
                    }
                })


            }
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayo = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                this?.let { it1 -> DatePickerDialog(it1, dateSetListener, year, month, dayo) }
            if (datePickerDialog != null) {
                datePickerDialog.show()
            }


            /*
            val dateprov = day.text.toString()
            val date = LocalDate.parse(dateprov, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            Log.e("uwu",date.toString())

*/




        }

        /************************************Classes by date and class*******************************************/



        /*******************************************************************************/
var events: ArrayList<IEvent?>?

        events = ArrayList()

        val eventColor = ContextCompat.getColor(this, R.color.bluemain)
        val timeStart = Calendar.getInstance()
        timeStart.set(Calendar.HOUR_OF_DAY, 18)
        timeStart.set(Calendar.MINUTE, 0)
        val timeEnd = timeStart.clone() as Calendar
        timeEnd.set(Calendar.HOUR_OF_DAY, 20)
        timeEnd.set(Calendar.MINUTE, 30)
        val event = Event(1, timeStart, timeEnd, "Another event", "Hockaido", eventColor)
        events!!.add(event);
        Log.e("calendar", "you are here")

        Log.e("calendar", timeStart.toString())


        dayView.setEvents(events);

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

    }


}
