package com.example.emplist.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.emplist.R
import com.example.emplist.common.onclickInterface
import com.example.emplist.data.models.DataX
import com.squareup.picasso.Picasso


private lateinit var context: Context
class PagingAdapter(contexts: Context,private val listener:onclickInterface) :
    PagingDataAdapter<DataX, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    init {
        context = contexts
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<DataX>() {
            override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean =
                oldItem == newItem
            override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? PagingadapterViewHolder)?.bind(item = getItem(position))
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> listener.onclick(it1) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PagingadapterViewHolder.getInstance(parent)
    }

    class PagingadapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun getInstance(parent: ViewGroup): PagingadapterViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.useradapeter, parent, false)
                return PagingadapterViewHolder(view)
            }
        }
        var images: ImageView = view.findViewById(R.id.user_image1)
        var firstname: TextView = view.findViewById(R.id.user_name1)
        var lastname: TextView = view.findViewById(R.id.last_name1)

        fun bind(item: DataX?) {
            item?.first_name?.let { firstname.text = it }
            item?.last_name?.let { lastname.text = it }
            Picasso.get().load(item?.avatar).into(images)

//            Glide.with(context).load(item?.avatar)
//                .apply(
//                    RequestOptions().override(800, 300)
//                ).into(images)
        }
    }

//    interface OnItemClickListener {
//        fun onItemClick(data: DataX)
//    }
}
