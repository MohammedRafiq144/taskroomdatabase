package com.example.taskdatasense.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskdatasense.R
import com.example.taskdatasense.RoomDatabase.UserDetails

class UserListAdapter(var context: Context): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private var list: ArrayList<UserDetails> = ArrayList()
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_itemview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.findViewById<TextView>(R.id.user_name).text = item.name
        holder.itemView.findViewById<TextView>(R.id.user_age).text = item.age
        holder.itemView.findViewById<TextView>(R.id.user_dob).text = item.dateOfBirth
        holder.itemView.findViewById<TextView>(R.id.user_education).text = item.education
        holder.itemView.findViewById<TextView>(R.id.user_gender).text = item.gender

    }
    fun resetView(filterData:List<UserDetails>) {
        this.list = filterData as ArrayList<UserDetails>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}