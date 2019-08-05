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
import company.locahost.itsc.dogetracker.R
import kotlinx.android.synthetic.main.listview_item_layout.view.*


/*class DogArrayAdapter: ArrayAdapter<Dog> {

    private lateinit var cont: Context
    private lateinit var rentalDogs: List<Dog>

    //constructor, call on creation
    constructor(context: Context, resource: Int, objects: ArrayList<Dog>) : super(context, resource, objects) {
        this.cont = context
        this.rentalDogs = objects
    }

    //called when rendering the list
    fun getMyView(position: Int, convertView: View, parent: ViewGroup): View {

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
        val completeAddress =
            dogs.getStreetNumber() + " " + dogs.getStreetName() + ", " + dogs.getSuburb() + ", " + dogs.getState()
        address.setText(completeAddress)

        //display trimmed excerpt for description
        val descriptionLength = property.getDescription().length()
        if (descriptionLength >= 100) {
            val descriptionTrim = property.getDescription().substring(0, 100) + "..."
            description.setText(descriptionTrim)
        } else {
            description.setText(property.getDescription())
        }

        //set price and rental attributes
        price.text = "$" + String.valueOf(property.getPrice())
        bedroom.text = "Bed: " + String.valueOf(property.getBedrooms())
        bathroom.text = "Bath: " + String.valueOf(property.getBathrooms())
        carspot.text = "Car: " + String.valueOf(property.getCarspots())

        //get the image associated with this property
        val imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName())
        image.setImageResource(imageID)

        return view
    }

}*/