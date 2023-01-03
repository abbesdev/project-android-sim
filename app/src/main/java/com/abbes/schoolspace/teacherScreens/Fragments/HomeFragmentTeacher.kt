package com.abbes.schoolspace.teacherScreens.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.AdapterHomework
import com.abbes.schoolspace.adapters.CustomAdapter
import com.abbes.schoolspace.adapters.CustomAdapterClassroomTeacher
import com.abbes.schoolspace.adapters.RecycleAdapterTimetable
import com.abbes.schoolspace.models.*
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragmentTeacher.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragmentTeacher : Fragment() {
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
        return inflater.inflate(R.layout.fragment_home_teacher, container, false)
    }
    private var lv: ListView? = null
    private var customeAdapter: CustomAdapterClassroomTeacher? = null
    var listVertical: ArrayList<ClassroomResponseItem> = arrayListOf()

    var list: ArrayList<TimetableItem> = arrayListOf()
    val adapter = RecycleAdapterTimetable(list, context)
    val layoutManager = LinearLayoutManager(context)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val unm = preferences?.getString("Unm", "not added yet")
        val fn = requireActivity().intent.getStringExtra("fullname") // OR Double quotes
        val unmId = preferences?.getString("userid", "not added yet")

        val textFn : (TextView) = view.findViewById(R.id.textView6)
        textFn.setText(unm)

        list = ArrayList()

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycleView)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://project-android-sim.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)
        val call: Call<Timetable> = api.getAllTimetable()

        call.enqueue(object : Callback<Timetable?> {
            @RequiresApi(Build.VERSION_CODES.O)
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Timetable?>, response: retrofit2.Response<Timetable?>) {
                if(response.isSuccessful){
                    list.clear()
                    val current = LocalDate.now()


                    for(myData in response.body()!!) {
                        val parts: List<String> = myData.startdate.split("T")
                        if(parts[0].contains(current.toString())){

                            list.add(myData)}
                    }
                    recyclerView.layoutManager = layoutManager
                    adapter.notifyDataSetChanged()
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    recyclerView.adapter= RecycleAdapterTimetable(list,context)
                }
            }

            override fun onFailure(call: Call<Timetable?>, t: Throwable) {
                Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
            }

        })




        lv = view.findViewById(R.id.userlistt) as ListView

        val call2: Call<ClassroomResponse> = api.getClassroomByTeacherId(unmId.toString())

        call2.enqueue(
            object : Callback<ClassroomResponse?> {

                override fun onResponse(
                    call: Call<ClassroomResponse?>,
                    response: retrofit2.Response<ClassroomResponse?>
                ) {

                    if (response.isSuccessful) {
                        listVertical.clear()
                        for (myData in response.body()!!) {
                            listVertical.add(myData)
                        }


                        customeAdapter = CustomAdapterClassroomTeacher(requireContext(), listVertical!!)
                        lv!!.adapter = customeAdapter

                    }
                }

                override fun onFailure(call: Call<ClassroomResponse?>, t: Throwable) {
                    Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment HomeFragmentTeacher.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragmentTeacher().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}