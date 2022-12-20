package com.abbes.schoolspace.ParentScreens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.CustomAdapterViewPager
import com.abbes.schoolspace.models.*
import com.abbes.schoolspace.rest.RestApi
import com.abbes.schoolspace.rest.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GradeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GradeListFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_grade_list, container, false)
    }
    private var lv: ListView? = null

    private var customeAdapterViewPager: CustomAdapterViewPager? = null

    var list: ArrayList<StudentWithGradeByIdModelItem> = arrayListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val preferences2 = this.activity?.getSharedPreferences("DetailActivity", Context.MODE_PRIVATE)

        val unm3 = preferences2?.getString("myStudent", "student null here")
        val unm2 = preferences2?.getString("mySubject", "subject null here")

        val subList : List<String> = arrayListOf(unm2.toString())
        val subListStudent : List<String> = arrayListOf(unm3.toString())

        val gradeInfo =  GradeVerifyPostRequest(
            subject = subList,
            students = subListStudent

        )
        Toast.makeText(context,gradeInfo.toString(), Toast.LENGTH_LONG).show()


        lv = view.findViewById(R.id.gradeList) as ListView
        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)

        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val unm = preferences?.getString("Unm3", "not added yet")

        api.getStudentGradesById(gradeInfo).enqueue(

            object : Callback<StudentWithGradeByIdModel> {
                override fun onFailure(call: Call<StudentWithGradeByIdModel>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<StudentWithGradeByIdModel>,
                    response: Response<StudentWithGradeByIdModel>
                ) {
                    Toast.makeText(context,response.body().toString(), Toast.LENGTH_LONG).show()




                    if(response.code() == 200){
                        list.clear()
                        for (myData in response.body()!!) {
                            list.add(myData)
                        }


                        customeAdapterViewPager = CustomAdapterViewPager(requireContext(), list)
                        lv!!.adapter = customeAdapterViewPager
                    }else{

                    }


                }

            })


    }
}