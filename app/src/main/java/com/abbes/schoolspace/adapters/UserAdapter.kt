package com.abbes.schoolspace.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.models.UsersInfItem
import com.abbes.schoolspace.rest.RestApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserAdapter(private val userList : ArrayList<UsersInfItem>): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.fullname.text = currentItem.fullname
        holder.email.text = currentItem.email




        val isVisible : Boolean = currentItem.visibility
        holder.layout.visibility = if (isVisible) View.VISIBLE else View.GONE


        holder.fullname.setOnClickListener {

            if (currentItem.confirmed== true){
                holder.accept.visibility = View.GONE
                currentItem.visibility = !currentItem.visibility
                notifyItemChanged(position)
            }else{
                currentItem.visibility = !currentItem.visibility
                notifyItemChanged(position)
            }


        }
        holder.delete.setOnClickListener{
            Log.e("delete","delete")
            val retrofitBuilder3 = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.22:8080/")
            .build()
            .create(RestApi::class.java)
            val retrofitData3 = retrofitBuilder3.deleteUser(currentItem._id)
            Log.e("dd",currentItem._id)

            retrofitData3.enqueue(object : Callback<ResponseBody> {


                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.e("response",response.code().toString())
                    if(response.code() == 200){
                        holder.linearLayout.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("response",t.toString())
                }

            })
        }

        holder.accept.setOnClickListener{
            Log.e("dd","88")
            val retrofitBuilder3 = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.22:8080/")
                .build()
                .create(RestApi::class.java)
            val retrofitData3 = retrofitBuilder3.updateUser(currentItem._id)
            Log.e("dd",currentItem._id)

            retrofitData3.enqueue(object : Callback<ResponseBody> {


                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.e("response",response.code().toString())
                    if(response.code() == 200){
                        holder.linearLayout.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("response",t.toString())
                }

            })
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_detail_user,
            parent,false)

        return UserAdapter.MyViewHolder(itemView)


    }


    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        val fullname: TextView = itemView.findViewById(R.id.fullname)
        val email: TextView = itemView.findViewById(R.id.email)
        val layout : LinearLayout = itemView.findViewById(R.id.expandedLayout)
        val accept : Button = itemView.findViewById(R.id.accept)
        val linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayout)

        val delete : Button = itemView.findViewById(R.id.button3)



    }

     }

/*


class StudentAdapter(private val userList : ArrayList<UserInfo>): RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_detail_user,
            parent,false)

        return MyViewHolder(itemView)


    }
    override fun onBindViewHolder(holder: StudentAdapter.MyViewHolder, position: Int) {

        val currentItem = userList[position]

        holder.fullname.text = currentItem.fullName
        holder.email.text = currentItem.email




        val isVisible : Boolean = currentItem.visibility
        holder.layout.visibility = if (isVisible) View.VISIBLE else View.GONE


        holder.fullname.setOnClickListener {

            if (currentItem.confirmed== true){
                holder.accept.visibility = View.GONE
                currentItem.visibility = !currentItem.visibility
                notifyItemChanged(position)
            }else{
                currentItem.visibility = !currentItem.visibility
                notifyItemChanged(position)
            }


        }

    }
    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        val fullname: TextView = itemView.findViewById(R.id.fullname)
        val email: TextView = itemView.findViewById(R.id.email)
        val layout : LinearLayout = itemView.findViewById(R.id.expandedLayout)
        val accept : Button = itemView.findViewById(R.id.accept)




    }
}
 */