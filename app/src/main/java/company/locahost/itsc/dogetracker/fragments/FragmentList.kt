package company.locahost.itsc.dogetracker.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import company.locahost.itsc.dogetracker.BaseActivity
import company.locahost.itsc.dogetracker.R
import company.locahost.itsc.dogetracker.dogs.Dog
import company.locahost.itsc.dogetracker.dogs.DogArrayAdapter
import company.locahost.itsc.dogetracker.dogs.NewDog
import company.locahost.itsc.dogetracker.profile.addDogData
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_list_layout.*
import kotlinx.android.synthetic.main.fragment_list_layout.view.*
import kotlinx.android.synthetic.main.fragment_list_layout.view.lw_dogs

class FragmentList : Fragment() {

    private lateinit var lwDogs: ListView

    private lateinit var ibNewDog: ImageButton
    companion object {
        lateinit var arrayList: ArrayList<Dog>
        val TAG = "FragmentList"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrayList = ArrayList()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view: View =
            inflater.inflate(company.locahost.itsc.dogetracker.R.layout.fragment_list_layout, container, false)
        registerForContextMenu(view)




        val dog = Dog("špic","6.8.2017","Bax","",24.0)
        arrayList.add(dog)

        ibNewDog = view.ib_addog
        lwDogs = view.lw_dogs
        ibNewDog.setOnClickListener { createDogAct() }
        return view

    }


    override fun onResume() {
        super.onResume()
        addDogData()

    }
    private fun createDogAct(){
        startActivity(Intent(activity, NewDog::class.java))
    }

    fun createArrayList(){

        val arrayAdapter: ArrayAdapter<Dog> = DogArrayAdapter(contexts,0, arrayList)

        lwDogs.adapter = arrayAdapter

    }

}