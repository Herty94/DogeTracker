package company.locahost.itsc.dogetracker.fragments

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import company.locahost.itsc.dogetracker.ConstantsDT

import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import androidx.core.app.ActivityCompat
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.R
import company.locahost.itsc.dogetracker.ConstantsDT.MAPVIEW_BUNDLE_KEY


class FragmentMap : Fragment() , OnMapReadyCallback {


    private lateinit var mMapView: MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(company.locahost.itsc.dogetracker.R.layout.fragmen_menu_layout,container,false)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null){
                mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
            mMapView = view.findViewById(company.locahost.itsc.dogetracker.R.id.mapView)
            mMapView.onCreate(mapViewBundle)
            mMapView.getMapAsync(this)


        return view
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mMapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onMapReady(map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity!!, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        map.isMyLocationEnabled = true
    }

    override fun onPause() {
        mMapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }
}