package com.example.app_laundry

class Order {
    var id_order : Int = 0
    var customer : String =""
    var no_tlp : String =""
    var id_cuci : Int = 0
    var berat : Int = 0
    var id_cus : Int = 0
    var status : String ="proses"
    var harga : Int = 0
    var date : String =""

    constructor(){
        this.id_cuci
        this.berat
        this.id_cus
        this.status
    }
    override fun toString(): String{
        return this.customer
    }

    @JvmName("getNama1")
    fun getName(): String? {
        return customer
    }
//    fun setNama(nama: String?) {
//        nama = customer!!
//    }

    @JvmName("getStatus1")
    fun getStatus(): String? {
        return status
    }
//    fun setTahun(tahun: String?) {
//        this.tahun = tahun!!
//    }
    @JvmName("getDate1")
    fun getDate(): String? {
        return date
    }
//    fun setJenis(jenis: String?) {
//        this.jenis = jenis!!
//    }
}