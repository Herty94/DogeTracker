package company.locahost.itsc.dogetracker.dogs

import java.util.*

class Dog {

    private var name: String =""
    private var breed: String? =""
    private var weight: Double? =0.0
    private var date: String?=""
    private var notes: String?=""
    private var imageUrl: String? =""



    constructor(breed: String?, date: String?, imageUrl: String? ,name: String,  notes: String?, weight: Double? ) {
        this.name = name
        this.breed = breed
        this.weight = weight
        this.date = date
        this.notes = notes
        this.imageUrl = imageUrl



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
    fun getImageUrl():String?{
        return this.imageUrl
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
    fun setImageUrl(imageUrl: String){
        this.imageUrl=imageUrl
    }



}