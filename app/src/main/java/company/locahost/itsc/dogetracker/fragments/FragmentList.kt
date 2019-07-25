package company.locahost.itsc.dogetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentList : Fragment() {

    companion object {
        val TAG = "FragmentList"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view: View =
            inflater.inflate(company.locahost.itsc.dogetracker.R.layout.fragment_list_layout, container, false)
        return view
    }
}