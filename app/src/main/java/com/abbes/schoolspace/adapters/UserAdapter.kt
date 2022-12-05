package com.abbes.schoolspace.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abbes.schoolspace.R
import com.abbes.schoolspace.models.UsersInfItem

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