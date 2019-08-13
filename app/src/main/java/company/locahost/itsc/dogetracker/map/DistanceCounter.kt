package company.locahost.itsc.dogetracker.map

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient

class DistanceCounter : Runnable {
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private var oldLocation: Location? = null
    private var newLocation: Location? = null

    private val TAG = "DistanceCounter"

    companion object {
        private var start: Boolean = false
    }

    constructor(fusedLocationCLient: FusedLocationProviderClient) {
        this.fusedLocationClient = fusedLocationClient
    }

    override fun run() {

        while (start) {

            checkForLocation()

        }

    }

    @SuppressLint("MissingPermission")
    private fun checkForLocation() {


        if (newLocation == null) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->

                    newLocation = location
                    Log.i(TAG, "Location:" + location)

                }
        } else {
        }

    }

}