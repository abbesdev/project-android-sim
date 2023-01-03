package com.abbes.schoolspace.teacherScreens.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.abbes.schoolspace.EditInfoActivity
import com.abbes.schoolspace.R
import com.abbes.schoolspace.SignInScreen

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragmentTeacher.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragmentTeacher : Fragment() {
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
        return inflater.inflate(R.layout.fragment_profile_teacher, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fn = requireActivity().intent.getStringExtra("fullname")
        val fnNum = requireActivity().intent.getStringExtra("numChild") // OR Double quotes
        val textFn : (TextView) = view.findViewById(R.id.texttitle11)
        val textFnNum : (TextView) = view.findViewById(R.id.texttitle111)
        val btnEdit : (LinearLayout) = view.findViewById(R.id.editMyInfoButton)
        val btnLogout : (LinearLayout) = view.findViewById(R.id.linearRoweditmyinfo1)

        val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)

        val unm = preferences?.getString("Unm", "not added yet")
        val unm2 = preferences?.getString("test", "not added yet")

        textFn.setText(unm)
        if (unm2 != null) {
            textFnNum.setText(unm2)
        }
        btnLogout.setOnClickListener{
            val preferences = this.activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)
            if (preferences != null) {
                preferences.edit().clear().commit()
                val intent = Intent (getActivity(), SignInScreen::class.java)
                getActivity()?.startActivity(intent)
            }
        }
        btnEdit.setOnClickListener {
            val intent = Intent (getActivity(), EditInfoActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragmentTeacher.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragmentTeacher().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}