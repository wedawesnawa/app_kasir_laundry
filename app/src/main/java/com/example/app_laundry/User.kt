package com.example.app_laundry

class User {
    var id_cus : Int=0
    var nama : String = ""
    var tlp : String =""
    var alamat : String =""

    constructor(nama: String, tlp:String, alamat:String){
        this.nama = nama
        this.tlp = tlp
        this.alamat = alamat
    }

    constructor()

    override fun toString(): String {
        return this.nama
    }
}