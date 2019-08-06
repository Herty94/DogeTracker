package company.locahost.itsc.dogetracker.dogs

import android.widget.ArrayAdapter


import android.widget.TextView
import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import company.locahost.itsc.dogetracker.R
import kotlinx.android.synthetic.main.listview_item_layout.view.*


class DogArrayAdapter: ArrayAdapter<Dog>, ListAdapter{

    private lateinit var contex: Context
    private lateinit var rentalDogs: List<Dog>

    //constructor, call on creation
    constructor(contex: Context, resource: Int, objects: ArrayList<Dog>) : super(contex, resource, objects) {
        this.contex = contex
        this.rentalDogs = objects
    }


    //called when rendering the list
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        //get the property we are displaying
        val dogs: Dog = rentalDogs[position]

        //get the inflater and inflate the XML layout for each item
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.listview_item_layout, null)

        val name: TextView = view.tv_dog_name
        val breed: TextView = view.tv_breed
        val weight: TextView = view.tv_weight
        val date: TextView = view.tv_date



        //set address and description

        name.text = ""+ dogs.getName()
        //set price and rental attributes
        breed.text =  "Breed: " + dogs.getBreed()
        weight.text = "Weight: " + (dogs.getWeight().toString())
        date.text = "BirthDay: " + dogs.getDate()


        //get the image associated with this property

        return view
    }

}