package com.abbes.schoolspace.StudentScreens.Fragments

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.AdapterHomework
import com.abbes.schoolspace.adapters.CourseAdapter
import com.abbes.schoolspace.adapters.CustomAdapter
import com.abbes.schoolspace.models.*
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeStudentFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_home_student, container, false)


    }


    private var lv: ListView? = null
    private var customeAdapter: AdapterHomework? = null
    var listVertical: ArrayList<HomeworkItem> = arrayListOf()

    var list: ArrayList<SubjectItem> = arrayListOf()
    val adapter = CourseAdapter(list, context)
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

        val textFn : (TextView) = view.findViewById(R.id.textView6)
        textFn.setText(unm)

        list = ArrayList()

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycleView)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://project-android-sim.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)
        val call: Call<Subject> = api.getAllSubjects()

        call.enqueue(object : Callback<Subject?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Subject?>, response: retrofit2.Response<Subject?>) {
                if(response.isSuccessful){
                    list.clear()
                    for(myData in response.body()!!) {
                        list.add(myData)
                    }
                    recyclerView.layoutManager = layoutManager
                    adapter.notifyDataSetChanged()
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    recyclerView.adapter= CourseAdapter(list,context)
                }
            }

            override fun onFailure(call: Call<Subject?>, t: Throwable) {
                Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
            }

        })




        lv = view.findViewById(R.id.userlistt) as ListView

        val call2: Call<Homework> = api.getHomeworkList()

        call2.enqueue(
            object : Callback<Homework?> {

                override fun onResponse(
                    call: Call<Homework?>,
                    response: retrofit2.Response<Homework?>
                ) {
                    Toast.makeText(context, response.code().toString(), Toast.LENGTH_LONG).show()

                    if (response.isSuccessful) {
                        listVertical.clear()
                        for (myData in response.body()!!) {
                            listVertical.add(myData)
                        }


                        customeAdapter = AdapterHomework(requireContext(), listVertical!!)
                        lv!!.adapter = customeAdapter

                    }
                }

                override fun onFailure(call: Call<Homework?>, t: Throwable) {
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
         * @return A new instance of fragment HomeStudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}