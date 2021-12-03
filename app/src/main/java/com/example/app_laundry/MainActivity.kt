 package com.example.app_laundry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.app_laundry.DBHelper.DBHelperOrder

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvAllTotal = findViewById<TextView>(R.id.tvAllTotal)
        val card_customer = findViewById<CardView>(R.id.card_customer)
        val card_order = findViewById<CardView>(R.id.card_order)
        val card_bayar = findViewById<CardView>(R.id.card_bayar)
        val card_listOrder = findViewById<CardView>(R.id.card_listOrder)

        val context = this
        val db = DBHelperOrder(context)
        val bayar = db.readBayar()

        if (bayar.isEmpty()){

            tvAllTotal.setText("Rp.0")
        }else{
            var total = db.readTotalBayar()
            tvAllTotal.setText("Rp."+total)
        }


//        var total = db.readTotalBayar()
//        tvAllTotal.setText("Rp."+total)


        card_customer.setOnClickListener{
            val intent = Intent(this@MainActivity , CustomerActivity::class.java)
            startActivity(intent)
        }
        card_order.setOnClickListener{
            startActivity(Intent(this@MainActivity, ActivityOrder::class.java))
        }
        card_listOrder.setOnClickListener{
            startActivity(Intent(this@MainActivity, ListOrderActivity::class.java))
        }
        card_bayar.setOnClickListener{
            startActivity(Intent(this@MainActivity, PembayaranActivity::class.java))
        }

    }

 }

