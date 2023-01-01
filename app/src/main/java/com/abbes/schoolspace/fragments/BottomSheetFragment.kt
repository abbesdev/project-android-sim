package com.abbes.schoolspace.fragments

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.abbes.schoolspace.models.Classe
import com.abbes.schoolspace.models.ClasseItem
import com.abbes.schoolspace.models.Matiere
import com.abbes.schoolspace.models.MatiereInfo
import com.abbes.schoolspace.rest.RestApi
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class BottomSheetFragment : RoundedBottomSheetDialogFragment() , TimePickerDialog.OnTimeSetListener{
    var hourstart = 0
    var minutestart = 0
    var hourend = 0
    var minuteend = 0
    var saveHour = 0
    var saveMinute = 0
    var hour = 0
    var minute = 0




    // val button : Button = findVie
//    val button = requireView().findViewById<Button>(com.abbes.schoolspace.R.id.testbut)
    //var imageView: ImageView = view!!.findViewById<View>(R.id.foo) as ImageView
 private lateinit var button: Button
 private lateinit var time: TextView
    private lateinit var timeend: TextView
    private lateinit var day: TextView
    private lateinit var spinner: Spinner
    private lateinit var warning: TextView
    private lateinit var warning2: TextView
    private lateinit var warningclass: TextView
    private lateinit var warningsubject: TextView
    private lateinit var spinner3: Spinner

    private lateinit var addclass: Button




/*   @RequiresApi(Build.VERSION_CODES.O)
   var timestartlocal = LocalTime.of(0,0)*/






    //   val button : Button = requireView().findViewById<Button>(com.abbes.schoolspace.R.id.testbut)
  //  var btn = findVi
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       //

        lateinit var timestartlocal : LocalTime
        lateinit var timeendlocal : LocalTime
        lateinit var datelocal : LocalDate


        lateinit var arrayAdapterr : ArrayAdapter<String>
        //   timestartlocal = LocalTime.of(0,0)
        val view =  inflater.inflate(com.abbes.schoolspace.R.layout.bottom_sheet_add_subject,container,false)
     time = view.findViewById(com.abbes.schoolspace.R.id.time)
        spinner3 = view.findViewById(com.abbes.schoolspace.R.id.spinner3)
        warning = view.findViewById(com.abbes.schoolspace.R.id.warning)
        warning.visibility = View.GONE
        warning2 = view.findViewById(com.abbes.schoolspace.R.id.warning2)
        warning2.visibility = View.GONE
        warningsubject = view.findViewById(com.abbes.schoolspace.R.id.warningsubject)
        warningsubject.visibility = View.GONE
        warningclass = view.findViewById(com.abbes.schoolspace.R.id.warningclass)
        warningclass.visibility = View.GONE
        addclass = view.findViewById(com.abbes.schoolspace.R.id.addclass)
        val items = arrayOf("Math", "SVT", "Technique","English","Francais")
        arrayAdapterr =
            context?.let { ArrayAdapter(it, R.layout.simple_spinner_dropdown_item,arrayOf("Select a subject")+items) }!!

        spinner3.adapter = arrayAdapterr

        timeend = view.findViewById(com.abbes.schoolspace.R.id.timeend)
        day = view.findViewById(com.abbes.schoolspace.R.id.day)

        //var  timestartlocal = LocalTime.of(0,0)
//        Log.e("ini",timestartlocal.toString())


        time.setOnClickListener {
            //  var timestartlocal : LocalTime
            // Do something when the button is clicked
     //     Toast.makeText(context, "button works",Toast.LENGTH_LONG).show()
        /* getDateTimeCalendar()
         TimePickerDialog(context,this,hour,minute,true).show()*/
         val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
             // Use the selected hour and minute to update a TextView or do something else
             var minut :String = ""
             var hourr :String = ""
             if(hour <10 )
                 hourr = "0"+hour.toString()
             else if (hour>9)
                 hourr = hour.toString()

             if(minute == 0)
                 minut = "00"
             else if(minute > 0 && minute <10)
                 minut = "0"+ minute.toString()
             else if(minute > 9)
                 minut = minute.toString()

             time.text = hourr +":"+minut


             val  timeendprov = timeend.text.toString()
             timeendlocal = LocalTime.parse(timeendprov)
             Log.e("ddd",timeendlocal.toString())
             val timestartprov = time.text.toString()
             timestartlocal = LocalTime.parse(timestartprov)
             if(timestartlocal.isAfter(timeendlocal))
                 warning.visibility = View.VISIBLE
             else if(timestartlocal.isBefore(timeendlocal))
                 warning.visibility = View.GONE
            // Log.e("before",timestartlocal.toString()+hour.toString()+minute.toString())



         }
           // Log.e("after",timestartlocal.toString()+hour.toString()+minute.toString())


            val timePickerDialog = TimePickerDialog(context, timeSetListener, hour, minute, true)
         timePickerDialog.show()



     }
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                // This code will be executed every 20 seconds
                //if(timestartlocal!=null)
                var text = time.text.toString();

                Log.e("timer2",text)
                //Log.e("timer3",timestartlocal.toString())

            }
        }
        timer.schedule(task, 0, 20000)
        timeend.setOnClickListener {
          //  Log.e("before end",timestartlocal.toString())

            // Do something when the button is clicked
            //     Toast.makeText(context, "button works",Toast.LENGTH_LONG).show()
            /* getDateTimeCalendar()
             TimePickerDialog(context,this,hour,minute,true).show()*/
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                // Use the selected hour and minute to update a TextView or do something else

                var minut :String = ""
                var hourr :String = ""
                if(hour <10 )
                    hourr = "0"+hour.toString()
                else if (hour>9)
                    hourr = hour.toString()

                if(minute == 0)
                    minut = "00"
                else if(minute > 0 && minute <10)
                    minut = "0"+ minute.toString()
                else if(minute > 9)
                    minut = minute.toString()

                timeend.text = hourr +":"+minut


             val  timeendprov = timeend.text.toString()
                timeendlocal = LocalTime.parse(timeendprov)
                Log.e("ddd",timeendlocal.toString())
                val timestartprov = time.text.toString()
                timestartlocal = LocalTime.parse(timestartprov)
               if(timestartlocal.isAfter(timeendlocal))
                   warning.visibility = View.VISIBLE
                else if(timestartlocal.isBefore(timeendlocal))
                   warning.visibility = View.GONE











            }
           // Log.e("ddd",timestartlocal.toString())


            val timePickerDialog = TimePickerDialog(context, timeSetListener, hour, minute, true)
            timePickerDialog.show()


        }

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

                day.text = dayofmon + "-" + mont+ "-" + year.toString()

                val dateprov = day.text.toString()
                val date = LocalDate.parse(dateprov, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                Log.e("uwu2",date.toString())
                if (date.isBefore(LocalDate.now())) {
                    // date is before today's date
                    warning2.visibility = View.VISIBLE
                }
                else
                    warning2.visibility = View.GONE

            }
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayo = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                context?.let { it1 -> DatePickerDialog(it1, dateSetListener, year, month, dayo) }
            if (datePickerDialog != null) {
                datePickerDialog.show()
            }
            val dateprov = day.text.toString()
            val date = LocalDate.parse(dateprov, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            Log.e("uwu",date.toString())




        }

        /**************** Get Classes List************************/

        var classes : ArrayList<String>
        classes =    arrayListOf<String>()
    //    day = view.findViewById(com.abbes.schoolspace.R.id.day)
        lateinit var ListClasses : ArrayList<ClasseItem>
        ListClasses = arrayListOf<ClasseItem>()

        lateinit var arrayAdapter : ArrayAdapter<String>

        spinner = view.findViewById(com.abbes.schoolspace.R.id.classes)
         arrayAdapter =
             context?.let { ArrayAdapter(it, R.layout.simple_spinner_dropdown_item,classes) }!!
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
                    classes.add("Select a class")
                    for(i in ListClasses.indices){
                        Log.e("deta",ListClasses[i].nameClasse.toString())
                        classes.add(ListClasses[i].nameClasse)

                        //      classes += ListClasses[i].nameClasse
                        Log.e("details",classes.toString())
                        var x = 1

                    }
                    Log.e("classin",classes.toString())

                    spinner.adapter = arrayAdapter
                }
            }

            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
/************************** Post class***********************************/
addclass.setOnClickListener{
    lateinit var newArrayList : ArrayList<MatiereInfo>
    lateinit var starttimesend : Date
    lateinit var endtimesend : Date

    lateinit var selectedItem : String
    lateinit var selectedItem2 : String


    val  timeendprov = timeend.text.toString()
    timeendlocal = LocalTime.parse(timeendprov)
    val  timestartprov = time.text.toString()
    timestartlocal = LocalTime.parse(timestartprov)
    val dateprov = day.text.toString()
    val date = LocalDate.parse(dateprov, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    val localdatetimestart = LocalDateTime.of(date,timestartlocal)
    val localdatetimeend = LocalDateTime.of(date,timeendlocal)


    starttimesend = Date.from(localdatetimestart.atZone(ZoneId.systemDefault()).toInstant())
    endtimesend = Date.from(localdatetimeend.atZone(ZoneId.systemDefault()).toInstant())


    //newArrayList = arrayListOf<MatiereInfo>()
  //  val classes = MatiereInfo(1,"", listOf("63985222919d478ba29899bc"),endtimesend,starttimesend,classroom[i])

    selectedItem = spinner.selectedItem.toString()
     selectedItem2 = spinner3.selectedItem.toString()
if(selectedItem == "Select a class")
    warningclass.visibility = View.VISIBLE
    else
    warningclass.visibility = View.GONE

  //  newArrayList = arrayListOf<MatiereInfo>()



    if(selectedItem2 == "Select a subject")
        warningsubject.visibility = View.VISIBLE
    else
        warningsubject.visibility = View.GONE



    if(warning.isVisible == true || warning2.isVisible == true || selectedItem == "Select a class"|| selectedItem2 == "Select a subject"){
        Toast.makeText(context, "problem", Toast.LENGTH_LONG).show()
    }
    else{
        Toast.makeText(context, "no problem", Toast.LENGTH_LONG).show()
     //   val classes = MatiereInfo(1,"", listOf("63985222919d478ba29899bc"),endtimesend,starttimesend,selectedItem2)
      //  Log.e("plzwork",classes.toString())






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
                if(response.isSuccessful){
                    ListClasses = response.body()!!
                    Log.e("response here ",response.body().toString())
                    for(i in ListClasses.indices){
                        Log.e("for here ",i.toString()+" -- "+ListClasses[i].toString())
                       if(ListClasses[i].nameClasse == selectedItem) {
                           Log.e("if here ",i.toString())

                           val classes2 = MatiereInfo(
                               1,
                               "",
                               listOf(ListClasses[i]._id),
                               endtimesend,
                               starttimesend,
                               selectedItem2
                           )
                           val retrofitBuilder = Retrofit.Builder()
                               .addConverterFactory(GsonConverterFactory.create())
                               .baseUrl("http://192.168.1.22:8080/")
                               .build()
                               .create(RestApi::class.java)
                           val retrofitData = retrofitBuilder.createTimetable(classes2)
                           if (retrofitData != null) {
                               retrofitData.enqueue(object : Callback<Matiere?> {
                                   override fun onResponse(call: Call<Matiere?>, response: Response<Matiere?>) {

                                       Log.e("post onresponse",response.code().toString())
                                   }

                                   override fun onFailure(call: Call<Matiere?>, t: Throwable) {

                                       Log.e("post onfailure",t.toString())

                                   }



                               })
                           }
                       }

                    }
                    /********************************/


                    /**********************************/

                }
            }

            override fun onFailure(call: Call<Classe>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
            }
        })











        }
/*********************************************************************************/




}
return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    private fun getDateTimeCalendar(){
        val cal  = Calendar.getInstance()
      /*  hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)*/

    }
    private fun pickDate(){

    //  test.setOn

    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
      /*  saveHour = hourOfDay
        saveMinute = minute*/

     //   time.text = saveHour.toString() + ":" + saveMinute.toString()

    }
}