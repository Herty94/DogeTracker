package company.locahost.itsc.dogetracker.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import company.locahost.itsc.dogetracker.BaseActivity
import company.locahost.itsc.dogetracker.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_list_layout.*
import kotlinx.android.synthetic.main.fragment_list_layout.view.*
import kotlinx.android.synthetic.main.fragment_list_layout.view.lw_dogs

class FragmentList : Fragment() {

    private lateinit var lwDogs: ListView

    private lateinit var contexts: Context

    companion object {
        val TAG = "FragmentList"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view: View =
            inflater.inflate(company.locahost.itsc.dogetracker.R.layout.fragment_list_layout, container, false)

        contexts=activity!!.applicationContext

        lwDogs = view.lw_dogs
        createArrayList()
        return view
    }

    private fun createArrayList(){
        var arrayList: ArrayList<String> = ArrayList()

        arrayList.add("pes1")
        arrayList.add("pes2")

        val arrayAdapter = ArrayAdapter(contexts,android.R.layout.simple_list_item_1,arrayList)

        lwDogs.adapter = arrayAdapter

    }
}