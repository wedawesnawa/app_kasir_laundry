package com.example.app_laundry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class OrderCusAdapter(context: Context, resource: Int, var item: ArrayList<Order>) :
    ArrayAdapter<Order>(context, resource, item){
    private var LCusOrder : List<Order> = item
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override  fun getItemId(position:Int): Long{
        return LCusOrder.get(position).id_order.toLong()
    }
    override fun getView(position: Int, convertView: View?, container: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item,container,false)
        }
        var text = getItem(position)!!.customer +" ("+ getItem(position)!!.no_tlp+")"
        (view?.findViewById(android.R.id.text1) as TextView).text = text
        return view
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item,parent,false)
        }
        (view?.findViewById(android.R.id.text1) as TextView).text = getItem(position)!!.customer
        return view
    }
}