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
    fun setName(name: String){
        this.name = name
    }
    fun setBreed(breed: String){
        this.breed = breed
    }
    fun setWeight(weight: Double){
        this.weight = weight
    }
    fun setDate(date: String){
        this.date = date
    }
    fun setNotes(notes: String){
        this.notes = notes
    }



}