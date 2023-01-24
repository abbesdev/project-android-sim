package com.abbes.schoolspace.ParentScreens

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.ClasseAdapter
import com.abbes.schoolspace.models.Classe
import com.abbes.schoolspace.models.ClasseItem
import com.abbes.schoolspace.models.Matiere
import com.abbes.schoolspace.models.MatiereInfo
import com.abbes.schoolspace.rest.RestApi
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.*
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.ZoneId
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var newRecylerview : RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val unm = preferences?.getString("Unm3", "not added yet")
        val unm2 = preferences?.getString("Unm", "not added yet")
        val uuid = preferences?.getString("userid", "638628bb762241552fd5b1a4")

        val calendarView = view.findViewById(R.id.calendarView2) as MaterialCalendarView
        newRecylerview =view.findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(context)
        newRecylerview.setHasFixedSize(true)
        /***************************** Dot Decorator **************************************************/
        lateinit var newArrayList2 : ArrayList<MatiereInfo>
        var thedate = arrayListOf<LocalDate>()

        newArrayList2 = arrayListOf<MatiereInfo>()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://project-android-sim.vercel.app/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getTimetableByParentId(uuid.toString())
        retrofitData.enqueue(object : Callback<Matiere> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<Matiere>,
                response: retrofit2.Response<Matiere>
            ) {
                // Log.e("kbsk",response.code().toString())


          //      Toast.makeText(applicationContext, response.code().toString()+" ", Toast.LENGTH_LONG).show()
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
               // Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                //  Log.e("errr",t.toString())

            }

        })
        /************************************************************************************/

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


        val textFn : (TextView) = view.findViewById(R.id.textView6)
        textFn.setText(unm2)
    }
    private fun getUsers(selectedDate : LocalDate){

        lateinit var newArrayList2 : ArrayList<MatiereInfo>
        lateinit var newArrayList : ArrayList<MatiereInfo>

        newArrayList2 = arrayListOf<MatiereInfo>()
        //    newArrayList2 = mutableListOf<MatiereInfo>()

        newArrayList = arrayListOf<MatiereInfo>()


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://project-android-sim.vercel.app/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getTimetableByParentId("638628bb762241552fd5b1a4")
        retrofitData.enqueue(object : Callback<Matiere> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<Matiere>,
                response: retrofit2.Response<Matiere>
            ) {
                // Log.e("kbsk",response.code().toString())


                //Toast.makeText(applicationContext, response.code().toString()+" ",Toast.LENGTH_LONG).show()
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
                            .baseUrl("https://project-android-sim.vercel.app/")
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
                              //  Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                            }
                        })

                        /*********************/


                    }
                  //  Log.e("lesdatess",thedate.toString())

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
               // Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
                //  Log.e("errr",t.toString())

            }
        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}