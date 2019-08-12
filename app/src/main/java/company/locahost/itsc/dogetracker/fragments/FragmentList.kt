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
import com.google.firebase.database.*
import company.locahost.itsc.dogetracker.BaseActivity
import company.locahost.itsc.dogetracker.ConstantsDT
import company.locahost.itsc.dogetracker.DogActivity
import company.locahost.itsc.dogetracker.R
import company.locahost.itsc.dogetracker.dogs.Dog
import company.locahost.itsc.dogetracker.dogs.DogArrayAdapter
import company.locahost.itsc.dogetracker.dogs.NewDog

import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_list_layout.*
import kotlinx.android.synthetic.main.fragment_list_layout.view.*
import kotlinx.android.synthetic.main.fragment_list_layout.view.lw_dogs
import java.io.Serializable

class FragmentList : Fragment(){

    private lateinit var lwDogs: ListView
    private lateinit var contexts: Context
    private lateinit var ibNewDog: ImageButton

    private var firebasedatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var myRef: DatabaseReference = firebasedatabase.getReference()

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

        Log.i(TAG,"OnCreateView context creating")


        contexts= activity!!.baseContext

        val dog = Dog("špic","6.8.2017","","Bax","",24.0)
        arrayList.add(dog)

        ibNewDog = view.ib_addog
        lwDogs = view.lw_dogs

        lwDogs.setOnItemClickListener { adapterView, view, i, l ->
            Log.i(TAG,"position: "+i+" id: "+l+" dog: "+arrayList.get(i))
            var intent = Intent(activity!!, DogActivity::class.java)
            intent.putExtra("arrayListId",i)
            startActivity(intent)


        }
        ibNewDog.setOnClickListener { createDogAct() }
        return view

    }
    fun addDogData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                arrayList.clear()
                for(ds in dataSnapshot.child("user_dogs").child(ConstantsDT.userAuth.uid!!).children){
                    Log.i(TAG,"Snapshot: " )
                    FragmentList.arrayList.add(ds.getValue(Dog::class.java)!!)

                    //FragmentList.arrayList.add(ds.child(ds.key!!).getValue(Dog::class.java)!!)
                }
                createArrayList()
                //
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(FragmentList.TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        myRef.addValueEventListener(postListener)
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume started")
        addDogData()


    }
    private fun createDogAct(){
        startActivity(Intent(activity, NewDog::class.java))
    }

    fun createArrayList(){



        Log.d(TAG,"context: "+ contexts)
        val arrayAdapter: ArrayAdapter<Dog> = DogArrayAdapter(contexts,0, arrayList)

        lwDogs.adapter = arrayAdapter

    }

}