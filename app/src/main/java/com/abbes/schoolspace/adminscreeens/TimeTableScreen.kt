package com.abbes.schoolspace.adminscreeens

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.ClasseAdapter
import com.abbes.schoolspace.models.*
import com.abbes.schoolspace.rest.RestApi
import com.google.android.material.navigation.NavigationView
import com.prolificinteractive.materialcalendarview.*
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.*


class TimeTableScreen : AppCompatActivity() {
    private lateinit var toggle : ActionBarDrawerToggle
    lateinit var classroom : Array<String>
    lateinit var date : Array<LocalDate>
    lateinit var timestart : Array<LocalTime>
    lateinit var timeend : Array<LocalTime>
    private lateinit var newRecylerview : RecyclerView
    lateinit var thedate : ArrayList<LocalDate>

    lateinit var datestart : Array<Date>
   lateinit var dateend : Array<Date>
    lateinit var classes :Array<List<String>>
















    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var ListClasses : ArrayList<ClasseItem>
        ListClasses = arrayListOf<ClasseItem>()
        thedate = arrayListOf<LocalDate>()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_screen)
        val textView = findViewById<TextView>(R.id.see_details)
        val calendarView = findViewById<MaterialCalendarView>(R.id.calendarView)
       // val calendarView2 = findViewById<CalendarView>(R.id.calendarView)


        lateinit var newArrayList2 : ArrayList<MatiereInfo>
        lateinit var newArrayList : ArrayList<MatiereInfo>

        newArrayList2 = arrayListOf<MatiereInfo>()
        newArrayList = arrayListOf<MatiereInfo>()


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.22:8080/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getAllTimetables()
        retrofitData.enqueue(object : Callback<Matiere> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<Matiere>,
                response: retrofit2.Response<Matiere>
            ) {
                // Log.e("kbsk",response.code().toString())


                Toast.makeText(applicationContext, response.code().toString()+" ",Toast.LENGTH_LONG).show()
                if(response.isSuccessful){
                    newArrayList2 = response.body()!!
                    Log.e("kbsk",response.body().toString())

                    for(i in newArrayList2.indices){
                        val dates : LocalDate = newArrayList2[i].timestart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                        thedate.add(dates)
                       // val adapter = ClasseAdapter(newArrayList,selectedDate)
                        //   Log.e("lesdatess",thedate.toString())


                        //newRecylerview.adapter = adapter

                    }

                    Log.e("lesdatess",thedate.toString())
                    val dotDecorators = object : DayViewDecorator {
                        // private val dotDrawable = ContextCompat.getDrawable(this@TimeTableScreen, R.drawable.dot)!!

                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun shouldDecorate(day: CalendarDay?): Boolean {
                            val localDate = day?.let { LocalDate.of(it.year, day.month, day.day) }/*******/



                            /******/
                            return thedate.contains(localDate)        }

                        override fun decorate(view: DayViewFacade?) {
                            if (view != null) {
                                view.addSpan(DotSpan(5F, Color.BLUE))


                            }
                        }

                    }
                    calendarView.addDecorator(dotDecorators)


                    /*
                       val classes = MatiereInfo(1,classroom[i], classes[i],dateend[i],datestart[i],classroom[i])
            if(dates == selectedDate){
            newArrayList.add(classes)
            }

        }

        val adapter = ClasseAdapter(newArrayList,selectedDate)
        newRecylerview.adapter = adapter

                     */




                }



/*  else
                   Toast.makeText(applicationContext, "error",Toast.LENGTH_LONG).show()*/


                /*  for(UserX in response.body()!!){
                      newArrayList2.add(UserX)
                  }*/


                //Toast.makeText(applicationContext, "No Errors",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Matiere>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
                //  Log.e("errr",t.toString())

            }

        })
      //  calendarView.addDecorator(dotDecorators)




        val retrofitBuilder2 = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.22:8080/")
            .build()
            .create(RestApi::class.java)
        val retrofitData2 = retrofitBuilder2.getAllClasses()
        retrofitData2.enqueue(object : Callback<Classe> {
            override fun onResponse(
                call: Call<Classe>,
                response: retrofit2.Response<Classe>
            ) {
                // Log.e("kbsk",response.code().toString())


                //Toast.makeText(applicationContext, response.code().toString()+" ",Toast.LENGTH_LONG).show()
                if(response.isSuccessful){
                    ListClasses += response.body()!!

                    Log.e("inside",ListClasses.toString())



                }


/*  else
                   Toast.makeText(applicationContext, "error",Toast.LENGTH_LONG).show()*/


                /*  for(UserX in response.body()!!){
                      newArrayList2.add(UserX)
                  }*/


                //Toast.makeText(applicationContext, "No Errors",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
                //  Log.e("errr",t.toString())

            }
        })

       // Toast.makeText(applicationContext, ListClasses.toString(),Toast.LENGTH_LONG).show()
     Log.e("errr",ListClasses.toString())


       // Log.e("exx2",ListClasses.toString())
/*
        classroom = arrayOf("SVT","Maths","Geographie","Histoire","Sport","Musique","Informatique","Anglais")

       date = arrayOf(
           /*
            LocalDate.of(2022, Month.DECEMBER, 11),
            LocalDate.of(2022, Month.DECEMBER, 11),
            LocalDate.of(2022, Month.DECEMBER, 12),
            LocalDate.of(2022, Month.DECEMBER, 12),
            LocalDate.of(2022, Month.DECEMBER, 15),

            LocalDate.of(2022, Month.DECEMBER, 12),
            LocalDate.of(2022, Month.DECEMBER, 12),


            LocalDate.of(2022, Month.DECEMBER, 13)*/
        )


        datestart  = arrayOf(
            Date(122,11,11,22,15,0),
            Date(122,11,11,22,15,0),

            Date(122,11,11,22,15,0),
            Date(122,11,11,22,15,0),

            Date(2022,12,11,22,15,0),
            Date(2022,12,11,22,15,0),
            Date(2022,12,11,22,15,0),
            Date(2022,12,11,22,15,0)
 )
        dateend  = arrayOf(
            Date(122,11,13,22,15,0),
            Date(122,11,13,22,15,0),

            Date(122,11,14,22,15,0),
            Date(2022,11,11,22,15,0),

            Date(2022,12,11,22,15,0),
            Date(2022,12,11,22,15,0),
            Date(2022,12,11,22,15,0),
            Date(2022,12,11,22,15,0)
        )
        for(i in classroom.indices) {
            val dates: LocalDate =
                dateend[i].toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            date = date + dates
            Log.e("kbsk",date[i].toString())


        }

        classes = arrayOf(listOf("ee"),listOf("ee"),listOf("ee"),listOf("ee"),listOf("ee"),listOf("ee"),listOf("ee"),listOf("ee"),)


        timestart = arrayOf(
            LocalTime.of(12, 0),
            LocalTime.of(13, 30),
            LocalTime.of(8, 0),
            LocalTime.of(16, 30),
            LocalTime.of(9, 0),
            LocalTime.of(10, 30),
            LocalTime.of(9, 0),
            LocalTime.of(8, 0)
        )
        timeend = arrayOf(
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(10, 0),
            LocalTime.of(18, 30),
            LocalTime.of(10, 0),
            LocalTime.of(12, 30),

            LocalTime.of(11, 0),

            LocalTime.of(10, 0)

            )*//*
        classroom = arrayOf("SVT")
        date = arrayOf(
            LocalDate.of(2022, Month.DECEMBER, 11)
        )
        timestart = arrayOf(
            LocalTime.of(12, 15)
        )
        timeend = arrayOf(
            LocalTime.of(12, 15)
        )*/

        newRecylerview =findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)

        textView.setOnClickListener {
            // Create an Intent to start the new activity
          val intent = Intent(this, TimeTableDetailsScreen::class.java)
            // Start the new activity
            startActivity(intent)
          Toast.makeText(this, "TextView clicked!", Toast.LENGTH_SHORT).show()

        }

        //getUserdata(LocalDate.now())
        getUsers(LocalDate.now())


        Log.e("dattes",thedate.toString())

        //      getClasses()
       // Log.e("ex2x",ListClasses.toString())





        /* calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
             val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
             // Do something with the selected date
             newRecylerview.swapAdapter(null, false)

             getUserdata(selectedDate)



         }*/

        calendarView.setOnDateChangedListener(object : DatePicker.OnDateChangedListener,
            OnDateSelectedListener {


            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                val day = date.day
                val month = date.month
                val year = date.year

                // Create a LocalDate instance using the selected date
              val selectedDate = LocalDate.of(year, month, day)
             //   val selectedDate = LocalDate.of(2022, 11 + 1, 12)


                // Do something with the selected date
                newRecylerview.swapAdapter(null, false)
               // getUserdata(selectedDate)
                getUsers(selectedDate)

            }

            override fun onDateChanged(
                view: DatePicker?,
                year: Int,

                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                val day = dayOfMonth
                val month = monthOfYear
                val year = year
             //   Log.w("myApp", monthOfYear.toString());


                // Create a LocalDate instance using the selected date
                val selectedDate = LocalDate.of(year, month + 1, day)

                // Do something with the selected date
                newRecylerview.swapAdapter(null, false)
                //getUserdata(selectedDate)
                getUsers(selectedDate)


            }
        })



   //     calendarView.setOnDateChangedListener()
      //  calendarView.addDecorator(dotDecorators)
  //      calendarView.addDecorator(dotDecorator)






        /*********menu*****/

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val intent1 = Intent(applicationContext, TeacherList::class.java)
        val intent2 = Intent(applicationContext, StudentList::class.java)

        val intent3 = Intent(applicationContext, ParentList::class.java)
        val intent4 = Intent(applicationContext, TimeTableScreen::class.java)


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

        Log.e("outside",ListClasses.toString())



    }






    val dotDecorator = object : DayViewDecorator {
       // private val dotDrawable = ContextCompat.getDrawable(this@TimeTableScreen, R.drawable.dot)!!

        @RequiresApi(Build.VERSION_CODES.O)
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            val localDate = day?.let { LocalDate.of(it.year, day.month, day.day) }/*******/



            /******/
            return date.contains(localDate)        }

        override fun decorate(view: DayViewFacade?) {
            if (view != null) {
                view.addSpan(DotSpan(5F, Color.BLUE))


            }
        }

    }

    /*
     fun getClasses(): ArrayList<ClasseItem> {
      //  var ListClasses : ArrayList<ClasseItem>

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
                // Log.e("kbsk",response.code().toString())


                //Toast.makeText(applicationContext, response.code().toString()+" ",Toast.LENGTH_LONG).show()
                if(response.isSuccessful){
                    ListClasses = response.body()!!
                    Log.e("exx",ListClasses.toString())



                }


/*  else
                   Toast.makeText(applicationContext, "error",Toast.LENGTH_LONG).show()*/


                /*  for(UserX in response.body()!!){
                      newArrayList2.add(UserX)
                  }*/


                //Toast.makeText(applicationContext, "No Errors",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
                //  Log.e("errr",t.toString())

            }
        })
return ListClasses
    }*/

    private fun getUsers(selectedDate : LocalDate){

        lateinit var newArrayList2 : ArrayList<MatiereInfo>
        lateinit var newArrayList : ArrayList<MatiereInfo>

        newArrayList2 = arrayListOf<MatiereInfo>()
    //    newArrayList2 = mutableListOf<MatiereInfo>()

        newArrayList = arrayListOf<MatiereInfo>()


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.22:8080/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getAllTimetables()
        retrofitData.enqueue(object : Callback<Matiere> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<Matiere>,
                response: retrofit2.Response<Matiere>
            ) {
                // Log.e("kbsk",response.code().toString())


                Toast.makeText(applicationContext, response.code().toString()+" ",Toast.LENGTH_LONG).show()
                if(response.isSuccessful){
                    newArrayList2 = response.body()!!
                    Log.e("kbsk",response.body().toString())

                    /********************get classes with id**************************/


                    /**********************************************/



                    for(i in newArrayList2.indices){

                        val dates : LocalDate = newArrayList2[i].timestart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                        /****************/
                        lateinit var ListClasses : ArrayList<ClasseItem>
                        ListClasses = arrayListOf<ClasseItem>()


                        var classes2 : ArrayList<String>
                        classes2 =    arrayListOf<String>()

                        val retrofitBuilder3 = Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("http://192.168.1.22:8080/")
                            .build()
                            .create(RestApi::class.java)
                        val retrofitData3 = retrofitBuilder3.getAllClasses()
                        retrofitData3.enqueue(object : Callback<Classe> {
                            override fun onResponse(
                                call: Call<Classe>,
                                response: retrofit2.Response<Classe>
                            ) {
                                if(response.isSuccessful){
                                    ListClasses += response.body()!!
                                    Log.e("uwu",ListClasses.toString())
                                    for(i in ListClasses.indices){
                                        Log.e("deta",ListClasses[i].nameClasse.toString())
                                        classes2.add(ListClasses[i].nameClasse)
                                        for(j in newArrayList2.indices){
                                           // Log.e("yarabi",newArrayList2[j].classes[0] +"  ***  "+ ListClasses[i]._id )
                                            if(newArrayList2[j].classes[0] == ListClasses[i]._id )
                                            {
                                                //  val index = newArrayList2.indexOf(ListClasses[i]._id)



                                                //  newArrayList2[j].classes[0]. = ListClasses[i].nameClasse
                                                newArrayList2[j].test =  ListClasses[i].nameClasse


                                            }

                                        }

                                    }
                                    if(selectedDate == dates) {
                                        newArrayList.add(newArrayList2[i])
                                        //      Log.e("hné",newArrayList2[i].test )



                                    }
                                    // thedate.add(dates)

                                    val adapter = ClasseAdapter(newArrayList,selectedDate)
                                    //   Log.e("lesdatess",thedate.toString())


                                    newRecylerview.adapter = adapter

                                }
                            }

                            override fun onFailure(call: Call<Classe>, t: Throwable) {
                                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                            }
                        })

                        /*********************/


                    }
                    Log.e("lesdatess",thedate.toString())

                    /*
                       val classes = MatiereInfo(1,classroom[i], classes[i],dateend[i],datestart[i],classroom[i])
            if(dates == selectedDate){
            newArrayList.add(classes)
            }

        }

        val adapter = ClasseAdapter(newArrayList,selectedDate)
        newRecylerview.adapter = adapter

                     */




                }



/*  else
                   Toast.makeText(applicationContext, "error",Toast.LENGTH_LONG).show()*/


                /*  for(UserX in response.body()!!){
                      newArrayList2.add(UserX)
                  }*/


                //Toast.makeText(applicationContext, "No Errors",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Matiere>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
                //  Log.e("errr",t.toString())

            }
        })


    }


    /*
    val dotDecorator = object : DayViewDecorator(){
    fun shouldDecorate(day: LocalDate?): Boolean {


        @RequiresApi(Build.VERSION_CODES.O)
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            val localDate = day?.let { LocalDate.of(it.year, day.month, day.day) }
            return date.contains(localDate)
                }

        override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5F, Color.RED))
    }
    }*/
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUserdata(selectedDate: LocalDate) {
        /*
         val __v: Int,
    @SerializedName("_id") val classroom: String,
    val classes: List<String>,
    @SerializedName("enddate") val timeend: java.util.Date,
    @SerializedName("startdate") val timestart: java.util.Date,
    val title: String
         */
        lateinit var newArrayList : ArrayList<MatiereInfo>

        newArrayList = arrayListOf<MatiereInfo>()

        for(i in classroom.indices){
            val dates : LocalDate = dateend[i].toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
          //  Toast.makeText(this, dates.toString(), Toast.LENGTH_SHORT).show()

        //    Log.e("kbsk",dateend[i].toString())


        //    val date : LocalDate = currentItem.timestart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()


            val classes = MatiereInfo(1,classroom[i], classes[i],dateend[i],datestart[i],classroom[i])
            if(dates == selectedDate){
            newArrayList.add(classes)
            }
         //   dates.add(date)

        }

        val adapter = ClasseAdapter(newArrayList,selectedDate)
        newRecylerview.adapter = adapter

    }

 //   private val dates: HashSet<CalendarDay> = HashSet(dates)
   // var priceDay = priceText





}