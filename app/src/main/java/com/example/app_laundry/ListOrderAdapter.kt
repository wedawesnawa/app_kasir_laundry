package com.example.app_laundry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class ListOrderAdapter(private val itemListOrder: ArrayList<Order>) : RecyclerView.Adapter<ListOrderAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nama: TextView = view.findViewById(R.id.tfNama)
        var status: TextView = view.findViewById(R.id.tfStatus)
        var date: TextView = view.findViewById(R.id.tfDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_order, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val allItem = itemListOrder[position]
        holder.nama.text = allItem.getName()
        holder.status.text = allItem.getStatus()
        holder.date.text = allItem.getDate()
    }

    override fun getItemCount(): Int {
        return itemListOrder.size
    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOrderAdapter.ViewHolder =
//        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_order,parent,false))
}