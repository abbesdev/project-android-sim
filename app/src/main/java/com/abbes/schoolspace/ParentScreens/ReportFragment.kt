package com.abbes.schoolspace.ParentScreens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
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
 * Use the [ReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_report, container, false)
    }


    private var lv: ListView? = null

    private var customeAdapter: CustomAdapter? = null
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


        val child = view.findViewById(R.id.child) as TextView


        lv = view.findViewById(R.id.userlist) as ListView


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://project-android-sim.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)
        val call: Call<ClassroomResponse> = api.getAllClassrooms()

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
                        Toast.makeText(requireContext(), response.body().toString(), Toast.LENGTH_LONG).show()


                        customeAdapter = CustomAdapter(requireContext(), list!!)
                        lv!!.adapter = customeAdapter

                    }
                }

                override fun onFailure(call: Call<ClassroomResponse?>, t: Throwable) {
                    Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
                }

            })

        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val unm = preferences?.getString("Unm3", "not added yet")
        val unm2 = preferences?.getString("Unm", "not added yet")

        val textFn : (TextView) = view.findViewById(R.id.textView6)
        textFn.setText(unm2)
        val callTo: Call<UserByIdModel> = api.getCharacter(unm.toString())





        callTo.enqueue(object : Callback<UserByIdModel?> {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onResponse(call: Call<UserByIdModel?>, response: retrofit2.Response<UserByIdModel?>) {
                                Toast.makeText(context, response.code().toString(), Toast.LENGTH_LONG).show()

                                if(response.isSuccessful){
                                    if(response.body()?.fullname != null) {


                                       child.text = response.body()?.fullname}
                                    else{
                                        child.text = "unknowm yet"
                                    }

                                }
                            }

                            override fun onFailure(call: Call<UserByIdModel?>, t: Throwable) {
                                Toast.makeText(context, "error hereeee:::", Toast.LENGTH_LONG).show()
                                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()

                            }

                        })
    }








    }




