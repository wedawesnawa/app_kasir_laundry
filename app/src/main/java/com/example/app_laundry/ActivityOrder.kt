package com.example.app_laundry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.app_laundry.DBHelper.DBHelperOrder
import com.google.android.material.textfield.TextInputEditText


class ActivityOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_activity)
        var jenisId = 0
        var userId = 0
        val btnAdd = findViewById<Button>(R.id.btnAddOrder)
        val tfCustomer = findViewById<AutoCompleteTextView>(R.id.TfCustomer)
        val tfJenis = findViewById<AutoCompleteTextView>(R.id.TfJenis)
        val tfHarga = findViewById<EditText>(R.id.TfHarga)
        val tfBerat = findViewById<TextInputEditText>(R.id.TfBerat)
        val context = this
        val db = DBHelperOrder(context)

        //list customer
        var list = db.readCustomer();
        var itemList = CusAdapter(this,android.R.layout.simple_list_item_1,list)
        tfCustomer.setAdapter(itemList)

        tfCustomer.setOnItemClickListener { parent, _, position, _ ->
            var selectedUser = parent.getItemAtPosition(position) as User
            userId = selectedUser.id_cus
        }

        //list jenis cuci
        var list1 = db.readJenis()
        var itemlist1 = JnsAdapter(this, android.R.layout.simple_list_item_1,list1)
        tfJenis.setAdapter(itemlist1)
        tfJenis.threshold= 3
    
        tfJenis.setOnItemClickListener { parent, _, position, _ ->
            var selectedJenis = parent.getItemAtPosition(position) as JenisCuci
            jenisId = selectedJenis.id_cuci
            var harga = selectedJenis.harga
//            tfHarga.setText(jenisId.toString())
//            var harga_= db.readHargaJenis()
            tfHarga.setText(harga.toString())
        }

        btnAdd.setOnClickListener {
            if(tfCustomer.text.toString().isNotEmpty()&& tfJenis.text.toString().isNotEmpty()&& tfBerat.text.toString().isNotEmpty()
            ){
                val order = Order().apply {
                    berat = tfBerat.text.toString().toInt()
                    id_cuci = jenisId
                    id_cus = userId
                }
                db.addOrder(order)
                tfCustomer.text?.clear()
                tfJenis.text?.clear()
                tfHarga.text?.clear()
                tfBerat.text?.clear()
            }else {
                Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }
    }

}