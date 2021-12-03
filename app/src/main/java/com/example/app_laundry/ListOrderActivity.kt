package com.example.app_laundry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_laundry.DBHelper.DBHelperOrder

class ListOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_order)
        val context = this
        val db = DBHelperOrder(context)

        val itemListOrder : ArrayList<Order> = db.readOrderCustomer()
        val recyclerView: RecyclerView = findViewById(R.id.rvListOrder)
        var listOrder = ListOrderAdapter(itemListOrder)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listOrder
    }
}