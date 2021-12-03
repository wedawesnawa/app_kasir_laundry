package com.example.app_laundry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.app_laundry.DBHelper.DBHelperOrder


class CustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        val btnAdd = findViewById<Button>(R.id.btnAddCst)
        val nama = findViewById<EditText>(R.id.name)
        val tlp = findViewById<EditText>(R.id.tlp)
        val alamat = findViewById<EditText>(R.id.alamat)
        val context = this
        val db = DBHelperOrder(context)
        btnAdd.setOnClickListener {

            if(nama.text.toString().isNotEmpty()&& tlp.text.toString().isNotEmpty()
                && alamat.text.toString().isNotEmpty()
            ){
                val user = User(nama.text.toString(), tlp.text.toString(), alamat.text.toString())
                db.addCustomer(user)
                nama.text.clear()
                tlp.text.clear()
                alamat.text.clear()
            }else {
                Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}