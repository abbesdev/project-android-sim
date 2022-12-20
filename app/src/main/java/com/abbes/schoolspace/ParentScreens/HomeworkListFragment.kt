package com.abbes.schoolspace.ParentScreens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.AdapterHomework
import com.abbes.schoolspace.adapters.ExpandableHomeworkAdapter
import com.abbes.schoolspace.models.Homework
import com.abbes.schoolspace.models.HomeworkItem
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
 * Use the [HomeworkListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeworkListFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_homework_list, container, false)
    }

    lateinit var lv2: ListView

    lateinit var customeAdapterViewPager2: AdapterHomework

    var list: ArrayList<HomeworkItem> = arrayListOf()
    var expandableListDetail: HashMap<HomeworkItem, ArrayList<HomeworkItem>> = hashMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val preferences2 =
            this.activity?.getSharedPreferences("DetailActivity", Context.MODE_PRIVATE)

        val unm3 = preferences2?.getString("myStudent", "student null here")
        val unm2 = preferences2?.getString("mySubject", "subject null here")

        val subList: List<String> = arrayListOf(unm2.toString())
        val subListStudent: List<String> = arrayListOf(unm3.toString())




        lv2 = view.findViewById(R.id.homeList1) as ListView
        val api: RestApi = ServiceBuilder.buildService(RestApi::class.java)

        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val unm = preferences?.getString("Unm3", "not added yet")

        api.getHomeworkList().enqueue(

            object : Callback<Homework> {
                override fun onFailure(call: Call<Homework>, t: Throwable) {


                }
                override fun onResponse(
                    call: Call<Homework>,
                    response: Response<Homework>
                ) {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show()




                    if (response.code() == 200) {
                        list.clear()
                        for (myData in response.body()!!) {
                            list.add(myData)
                        }

                        customeAdapterViewPager2 = AdapterHomework(context!!, list)

                        lv2.adapter = customeAdapterViewPager2

                    } else {

                    }


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
         * @return A new instance of fragment HomeworkListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeworkListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}