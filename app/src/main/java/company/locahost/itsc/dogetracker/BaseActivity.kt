package company.locahost.itsc.dogetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import company.locahost.itsc.dogetracker.login.Signin
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.widget.PopupMenu
import com.google.android.gms.maps.*
import kotlinx.android.synthetic.main.activity_base.view.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap





class BaseActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener , OnMapReadyCallback{
    private lateinit var signin: Signin
    private lateinit var name: String
    private lateinit var mapFragment: MapFragment
    private lateinit var googleMap: GoogleMap
    private val TAG = "BaseActivity"
    private lateinit var tvUser: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_base)

        if (supportFragmentManager.findFragmentById(R.id.mapView) != null){
            mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as MapFragment
           mapFragment.getMapAsync(this)}


        signin = Signin()
        tvUser = findViewById(R.id.tv_user)
        ConstantsDT.userAuth = FirebaseAuth.getInstance()

    }
    override fun onMapReady(map: GoogleMap) {
        val sydney = LatLng(-33.867, 151.206)

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13f))

        map.addMarker(
            MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney)
        )
    }


    override fun onStart() {
        super.onStart()
        checkCurrentUser()

    }

    public fun showPopup(v: View) {
        var popup = PopupMenu(this , v)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.base_menu)
        popup.show()

    }

    private fun checkCurrentUser(){

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        if (user != null){
            Log.i(TAG,"User is signed In "+user.displayName)
            if (user.email == null) ConstantsDT.userEmail="Anonym"
            else ConstantsDT.userEmail = ""+user.email
            tvUser.setText(ConstantsDT.userEmail)



        }else{
            startActivity(Intent(this, Signin::class.java))
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.mn_logout -> { ConstantsDT.userAuth.signOut()
                    checkCurrentUser()
                    return true}

                else -> return false
            }
        }
        else
        return false
    }

}

