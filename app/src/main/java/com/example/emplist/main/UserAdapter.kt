package com.example.emplist.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emplist.common.onclickInterface
import com.example.emplist.databinding.UseradapeterBinding
import com.example.emplist.data.models.DataX
import com.squareup.picasso.Picasso

class UserAdapter(context: Context,private val listener:onclickInterface) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var context = context

    private var arrayList: MutableList<DataX> = mutableListOf()
    fun setUserList(userList: List<DataX>) {
        arrayList.clear()
        arrayList.addAll(userList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UseradapeterBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onclick(arrayList.get(position))
        }
        holder.binding.userAdapter = arrayList[position]
        Picasso.get().load(arrayList[position].avatar).into(holder.binding.userImage1)
//        Glide.with(context).load(arrayList[position].avatar)
//            .apply(
//                RequestOptions().override(800, 300)
//            ).into(holder.binding.userImage1)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }




    inner class ViewHolder(binding: UseradapeterBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding
    }
}