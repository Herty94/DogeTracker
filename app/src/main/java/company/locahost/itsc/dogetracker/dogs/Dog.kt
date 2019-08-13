package company.locahost.itsc.dogetracker.dogs

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Dog : Parcelable{

    private var name: String =""
    private var breed: String? =""
    private var weight: Double? =0.0
    private var date: String?=""
    private var notes: String?=""
    private var imageUrl: String? =""

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()!!
        breed = parcel.readString()
        weight = parcel.readValue(Double::class.java.classLoader) as? Double
        date = parcel.readString()
        notes = parcel.readString()
        imageUrl = parcel.readString()
    }


    constructor(breed: String?, date: String?, imageUrl: String? ,name: String,  notes: String?, weight: Double? ) {
        this.name = name
        this.breed = breed
        this.weight = weight
        this.date = date
        this.notes = notes
        this.imageUrl = imageUrl



    }
    constructor(){}

    override fun writeToParcel(dest: Parcel?, p1: Int) {
        dest!!.writeString(name)
        dest!!.writeString(breed)
        if(weight != null){
            dest!!.writeDouble(weight!!)
        }else dest!!.writeDouble(0.0)
        dest!!.writeString(date)
        dest!!.writeString(notes)
        dest!!.writeString(imageUrl)
            //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        return 0;//To change body of created functions use File | Settings | File Templates.
    }

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

    companion object CREATOR : Parcelable.Creator<Dog> {
        override fun createFromParcel(parcel: Parcel): Dog {
            return Dog(parcel)
        }

        override fun newArray(size: Int): Array<Dog?> {
            return arrayOfNulls(size)
        }
    }


}