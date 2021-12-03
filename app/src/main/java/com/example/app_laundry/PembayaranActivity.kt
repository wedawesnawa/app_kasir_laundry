package com.example.app_laundry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.app_laundry.DBHelper.DBHelperOrder
import android.text.Editable

import android.text.TextWatcher




class PembayaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        var orderId = 0
        var berat = 0
        var harga = 0

        val tfNama = findViewById<AutoCompleteTextView>(R.id.TfNama)
        val tfTotal = findViewById<EditText>(R.id.TfTotal)
        val tfCaraBayar = findViewById<AutoCompleteTextView>(R.id.TfCaraBayar)
        val tfStatus = findViewById<AutoCompleteTextView>(R.id.TfStatus)
        val tfJumlahUang = findViewById<EditText>(R.id.TfJumlahUang)
        val tfKembalian = findViewById<EditText>(R.id.TfKembalian)
        val btnBayar = findViewById<Button>(R.id.btnBayar)

        val context = this
        val db = DBHelperOrder(context)

        //list customer
        var listNama = db.readOrderCustomer();
        var itemListNama = OrderCusAdapter(this,android.R.layout.simple_list_item_1, listNama)
        tfNama.setAdapter(itemListNama)
        tfNama.threshold = 3

        tfNama.setOnItemClickListener { parent, _, position, _ ->
            var selectedCus = parent.getItemAtPosition(position) as Order
            orderId = selectedCus.id_order
            berat = selectedCus.berat
            harga = selectedCus.harga
            var result = harga * berat
//            var noTlp = selectedCus.harga
            tfTotal.setText(result.toString())
        }
        //list cara bayar
        var listBayar = arrayOf("CASH","KREDIT")
        var itemListBayar = ArrayAdapter(this, android.R.layout.simple_list_item_1, listBayar)
        tfCaraBayar.setAdapter(itemListBayar)

        //list status
        var listStatus = arrayOf("Selesai","Belum Bayar")
        var itemListStatus = ArrayAdapter(this, android.R.layout.simple_list_item_1, listStatus)
        tfStatus.setAdapter(itemListStatus)

        //jumlahUang dikurang total
        tfJumlahUang.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (!tfJumlahUang.getText().toString().equals("") && !tfTotal.getText().toString().equals("")){
                    var jmlh_Uang = tfJumlahUang.getText().toString().toInt()
                    var total = tfTotal.getText().toString().toInt()

                    var resultSisa = jmlh_Uang - total
                    tfKembalian.setText(resultSisa.toString())
                    btnBayar.isEnabled = resultSisa > 0
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

                // Place the logic here for your output edittext
            }
        })

        //insert to table
        btnBayar.setOnClickListener {
            if(tfNama.text.toString().isNotEmpty()&& tfTotal.text.toString().isNotEmpty()&& tfCaraBayar.text.toString().isNotEmpty()
            ){
                val bayar = Bayar().apply {
                    id_order = orderId
                    cara_bayar = tfCaraBayar.text.toString()
                    status = tfStatus.text.toString()
                    total = tfTotal.text.toString().toInt()
                    jumlah = tfJumlahUang.text.toString().toInt()
                }
                db.addBayar(bayar)
                db.updateStatus(bayar)
                tfNama.text.clear()
                tfTotal.text.clear()
                tfCaraBayar.text.clear()
                tfJumlahUang.text.clear()
                tfKembalian.text.clear()
                tfStatus.text.clear()
            }else {
                Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}