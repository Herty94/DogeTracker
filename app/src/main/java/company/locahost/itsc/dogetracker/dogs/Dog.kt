package company.locahost.itsc.dogetracker.dogs

import java.util.*

class Dog {

    private var name: String =""
    private var breed: String? =""
    private var weight: Double? =0.0
    private var date: String?=""
    private var notes: String?=""



    constructor(breed: String?, date: String? ,name: String,  notes: String?, weight: Double?) {
        this.name = name
        this.breed = breed
        this.weight = weight
        this.date = date
        this.notes = notes



    }
    constructor(){}
    fun getName():String{
        return this.name
    }
    fun getBreed():String?{
        return this.breed
    }

    fun getWeight():Double?{
        return this.weight
    }

    fun getDate():String?{
        return this.date
    }

    fun getNotes():String?{
        return this.notes
    }



}