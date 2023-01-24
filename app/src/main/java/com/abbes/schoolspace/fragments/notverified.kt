package com.abbes.schoolspace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.abbes.schoolspace.R
import com.abbes.schoolspace.adapters.CustomAdapter
import com.abbes.schoolspace.adapters.StudentAdapter
import com.abbes.schoolspace.adapters.UserAdapter
import com.abbes.schoolspace.models.UserInfo
import com.abbes.schoolspace.models.UsersInf
import com.abbes.schoolspace.models.UsersInfItem
import com.abbes.schoolspace.rest.RestApi
import com.google.android.material.tabs.TabLayout
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
 * Use the [notverified.newInstance] factory method to
 * create an instance of this fragment.
 */
class notverified : Fragment() {
    private lateinit var newRecylerview : RecyclerView
    //private lateinit var viewPager: ViewPager
    private lateinit var viewPager : ViewPager
    private lateinit var tabs: TabLayout

    private lateinit var newArrayList : ArrayList<UserInfo>
    private lateinit var newArrayList2 : ArrayList<UsersInfItem>
    private lateinit var newArrayList3 : ArrayList<UsersInfItem>


    lateinit var fullname : Array<String>
    lateinit var email : Array<String>
    lateinit var confirmed : Array<Boolean>
    lateinit var password : Array<String>


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notverified, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment notverified.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                notverified().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fullname = arrayOf("Beyram ayadi","Mohammed Abbes")
        email =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        password =  arrayOf("beyram.ayadi@esprit.tn","mohammed.abbes@esprit.tn")
        confirmed = arrayOf(true,false)




        newRecylerview = view.findViewById(R.id.recyclerView)
        newRecylerview.layoutManager = LinearLayoutManager(getActivity())
        newRecylerview.setHasFixedSize(true)


        newArrayList = arrayListOf<UserInfo>()
       // getUserdata()



        getUsers()

    }
 /*   private fun getUserdata() {

        for(i in fullname.indices){
            if(confirmed[i]==false){

            val student = UserInfo(fullname[i],email[i], password[i],confirmed[i])
            newArrayList.add(student)}

        }
        val adapter = StudentAdapter(newArrayList)
        newRecylerview.adapter = adapter

    }*/
    private fun getUsers(){
        newArrayList2 = arrayListOf<UsersInfItem>()
        newArrayList3 = arrayListOf<UsersInfItem>()

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://project-android-sim.vercel.app/")
            .build()
            .create(RestApi::class.java)
        val retrofitData = retrofitBuilder.getAllUser()
        retrofitData.enqueue(object : Callback<UsersInf> {
            override fun onResponse(
                call: Call<UsersInf>,
                response: retrofit2.Response<UsersInf>
            ) {
                Toast.makeText(context, response.code().toString()+" "+response.message().toString(),
                    Toast.LENGTH_LONG).show()
                // Toast.makeText(applicationContext, response.code().toString()+" ", Toast.LENGTH_LONG).show()
                if(response.isSuccessful){
                    newArrayList2 = response.body()!!
                    Log.e("getusers",newArrayList2.toString())
                    for(i in newArrayList2.indices){
                        if(newArrayList2[i].confirmed==false && newArrayList2[i].roles[0]=="63862832351815a82140b1c2"){
                            Log.e("beyraam",i.toString())
                            //val student = UserInfo(fullname[i],email[i], password[i],confirmed[i])
                            newArrayList3.add(newArrayList2[i])}
                    }
                    val adapter = UserAdapter(newArrayList3)
                    newRecylerview.adapter = adapter
                }
            }
            override fun onFailure(call: Call<UsersInf?>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()

            }
        })
    }

}
