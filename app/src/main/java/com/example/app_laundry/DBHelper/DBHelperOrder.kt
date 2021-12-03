package com.example.app_laundry.DBHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.app_laundry.Bayar
import com.example.app_laundry.JenisCuci
import com.example.app_laundry.Order
import com.example.app_laundry.User

class DBHelperOrder(var context: Context): SQLiteOpenHelper(context,"laundry_db", null, 1){
    companion object{
        private val NAMA_TABLE ="customer"
        private val NAMA_TABLE1 ="order_laundry"
        private val NAMA_TABLE2 ="jenis_cuci"
        private val NAMA_TABLE3 ="bayaran"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //Create
        val CREATE_TABLE_QUERY : String = ("CREATE TABLE "+NAMA_TABLE1+"(id_order INTEGER PRIMARY KEY AUTOINCREMENT,status_order VARCHAR,id_cuci INTEGER,berat_pakaian INTEGER, id_pelanggan INTEGER, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)")
        val CREATE_TABLE_QUERY1 : String = ("CREATE TABLE "+NAMA_TABLE+"(id_pelanggan INTEGER PRIMARY KEY AUTOINCREMENT,nama_pelanggan VARCHAR, no_tlp VARCHAR, alamat VARCHAR)")
        val CREATE_TABLE_QUERY2 : String = ("CREATE TABLE "+NAMA_TABLE2+"(id_cuci INTEGER PRIMARY KEY AUTOINCREMENT,jenis_cuci VARCHAR, harga INTERGER)")
        val CREATE_TABLE_QUERY3 : String = ("CREATE TABLE "+NAMA_TABLE3+"(id_bayar INTEGER PRIMARY KEY AUTOINCREMENT,total INTEGER,cara_bayar VARCHAR,jumlah_uang INTEGER,status_order VARCHAR,id_order INTEGER)")
        //Insert
        val INSERT_TABLE_QUERY2 : String = ("INSERT INTO "+NAMA_TABLE2+"(id_cuci,jenis_cuci,harga) VALUES (1,'Cuci',5000),(2,'Cuci dan Jemur',7000),(3,'Cuci, Jemur dan Setrika',9000) ")
        //Create
        db!!.execSQL(CREATE_TABLE_QUERY)
        db!!.execSQL(CREATE_TABLE_QUERY1)
        db!!.execSQL(CREATE_TABLE_QUERY2)
        db!!.execSQL(CREATE_TABLE_QUERY3)
        //Insert
        db!!.execSQL(INSERT_TABLE_QUERY2)

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
        db!!.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE1")
        db!!.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE")
        db!!.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE2")
        db!!.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE3")
        onCreate(db)
    }

    //insert
    fun addOrder(odr: Order):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id_cuci", odr.id_cuci)
        contentValues.put("berat_pakaian", odr.berat)
        contentValues.put("id_pelanggan", odr.id_cus)
        contentValues.put("status_order", odr.status)

        val result = db.insert(NAMA_TABLE1, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
        return result
    }
    fun addCustomer(user: User):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nama_pelanggan", user.nama)
        contentValues.put("no_tlp", user.tlp)
        contentValues.put("alamat", user.alamat)

        val result = db.insert(NAMA_TABLE, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
        return result
    }
    fun addBayar(bayar: Bayar):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id_order", bayar.id_order)
        contentValues.put("total", bayar.total)
        contentValues.put("cara_bayar", bayar.cara_bayar)
        contentValues.put("jumlah_uang", bayar.jumlah)
        contentValues.put("status_order", bayar.status)

        val result = db.insert(NAMA_TABLE3, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
        return result
    }

    //Update
    fun updateStatus(bayar: Bayar): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("status_order", bayar.status)

        val result = db.update(NAMA_TABLE1,contentValues, "id_order=" + bayar.id_order, null)
        db.close()
        return result
    }


    //select
    @SuppressLint("Range")
    fun readCustomer(): ArrayList<User> {
        var list : ArrayList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $NAMA_TABLE"
        var result: Cursor? = null
        result = db.rawQuery(query,null,)
//        var result = db.rawQuery(query,null,)
        if(result.moveToFirst()){
            do{
                var user = User()
                user.nama = result.getString(result.getColumnIndex("nama_pelanggan"))
                user.id_cus = result.getString(result.getColumnIndex("id_pelanggan")).toInt()
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        return list
    }
    @SuppressLint("Range")
    fun readJenis(): MutableList<JenisCuci> {
        val listCuci : MutableList<JenisCuci> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $NAMA_TABLE2"
//        var result: Cursor? = null
//        result = db.rawQuery(query,null,)

        var result = db.rawQuery(query,null,)
        if(result.moveToFirst()){
            do{
                var jenis = JenisCuci()
                jenis.jenis_cuci = result.getString(result.getColumnIndex("jenis_cuci"))
                jenis.id_cuci = result.getString(result.getColumnIndex("id_cuci")).toInt()
                jenis.harga = result.getString(result.getColumnIndex("harga")).toInt()

                listCuci.add(jenis)
            }while (result.moveToNext())
        }
        result.close()
        return listCuci
    }
    @SuppressLint("Range")
    fun readOrderCustomer(): ArrayList<Order> {
        var listOrder : ArrayList<Order> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM (($NAMA_TABLE1"+" INNER JOIN $NAMA_TABLE ON $NAMA_TABLE1.id_pelanggan = $NAMA_TABLE.id_pelanggan)INNER JOIN $NAMA_TABLE2 ON $NAMA_TABLE1.id_cuci = $NAMA_TABLE2.id_cuci)"
        var result: Cursor? = null
        result = db.rawQuery(query,null,)
//        var result = db.rawQuery(query,null,)
        if(result.moveToFirst()){
            do{
                var order = Order()
                order.customer = result.getString(result.getColumnIndex("nama_pelanggan"))
                order.no_tlp = result.getString(result.getColumnIndex("no_tlp"))
                order.id_order = result.getString(result.getColumnIndex("id_pelanggan")).toInt()
                order.berat = result.getString(result.getColumnIndex("berat_pakaian")).toInt()
                order.harga = result.getString(result.getColumnIndex("harga")).toInt()
                order.status = result.getString(result.getColumnIndex("status_order"))
                order.date = result.getString(result.getColumnIndex("created_at"))
                listOrder.add(order)
            }while (result.moveToNext())
        }
        result.close()
        return listOrder
    }
    @SuppressLint("Range")
    fun readTotalBayar() : MutableList<Bayar>{
        val totalBayar : MutableList<Bayar> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT SUM(total) AS'AllTotal' FROM $NAMA_TABLE3 WHERE total >=0"
        var result: Cursor? = null
        result = db.rawQuery(query, null)
            if (result.moveToFirst()) {
                do {
                    var bayar = Bayar()
                    bayar.allTotal = result.getString(result.getColumnIndex("AllTotal"))

                    totalBayar.add(bayar)
                } while (result.moveToNext())
            }
        result.close()
        return totalBayar
    }
    @SuppressLint("Range")
    fun readBayar() : MutableList<Bayar>{
        val totalBayar : MutableList<Bayar> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $NAMA_TABLE3 "
        var result: Cursor? = null
        result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var bayar = Bayar()
                bayar.total = result.getString(result.getColumnIndex("total")).toInt()

                totalBayar.add(bayar)
            } while (result.moveToNext())
        }
        result.close()
        return totalBayar
    }
}