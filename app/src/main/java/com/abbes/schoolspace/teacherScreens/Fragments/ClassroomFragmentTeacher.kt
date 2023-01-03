package com.abbes.schoolspace.teacherScreens.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.abbes.schoolspace.ParentScreens.DetailActivity
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.CustomAdapter
import com.abbes.schoolspace.adapters.CustomAdapterClassroomTeacher
import com.abbes.schoolspace.models.ClassroomResponse
import com.abbes.schoolspace.models.ClassroomResponseItem
import com.abbes.schoolspace.models.ImageModel
import com.abbes.schoolspace.models.UserByIdModel
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import com.abbes.schoolspace.teacherScreens.AddClassroomActivity
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
 * Use the [ClassroomFragmentTeacher.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassroomFragmentTeacher : Fragment() {
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
        return inflater.inflate(R.layout.fragment_classroom_teacher, container, false)
    }

    private var lv: ListView? = null

    private var customeAdapter: CustomAdapterClassroomTeacher? = null
    private var imageModelArrayList: ArrayList<ImageModel>? = null
    private val myImageList = intArrayOf(
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large,
        R.drawable.high_school_physics_120415_large
    )
    lateinit var customArrayAdapters: CustomAdapter


    var list: ArrayList<ClassroomResponseItem> = arrayListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
val btnAdd = view.findViewById(R.id.classroomaddbtn) as Button

        btnAdd.setOnClickListener({
            val intent = Intent(context, AddClassroomActivity::class.java)
            startActivity(intent)
        })


        lv = view.findViewById(R.id.userlist) as ListView


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://project-android-sim.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val unmId = preferences?.getString("userid", "not added yet")
        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)
        val call: Call<ClassroomResponse> = api.getClassroomByTeacherId(unmId.toString())

        call.enqueue(
            object : Callback<ClassroomResponse?> {


                override fun onResponse(
                    call: Call<ClassroomResponse?>,
                    response: retrofit2.Response<ClassroomResponse?>
                ) {


                    if (response.isSuccessful) {
                        list.clear()
                        for (myData in response.body()!!) {
                            list.add(myData)
                        }


                        customeAdapter = CustomAdapterClassroomTeacher(requireContext(), list!!)
                        lv!!.adapter = customeAdapter

                    }
                }

                override fun onFailure(call: Call<ClassroomResponse?>, t: Throwable) {
                    Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
                }

            })

        val unm = preferences?.getString("Unm3", "not added yet")
        val unm2 = preferences?.getString("Unm", "not added yet")

        val textFn : (TextView) = view.findViewById(R.id.textView6)
        textFn.setText(unm2)







    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClassroomFragmentTeacher.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClassroomFragmentTeacher().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}