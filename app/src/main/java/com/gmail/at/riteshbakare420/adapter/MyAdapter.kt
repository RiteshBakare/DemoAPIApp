package com.gmail.at.riteshbakare420.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.at.riteshbakare420.R
import com.gmail.at.riteshbakare420.models.Data

class MyAdapter(private val list: List<Data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.tvName)
        val no: TextView = itemView.findViewById<TextView>(R.id.tvNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.demo_tile,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.name.text = data.name
        holder.no.text = data.no
    }
}
