package company.locahost.itsc.dogetracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import company.locahost.itsc.dogetracker.dogs.NewDog
import company.locahost.itsc.dogetracker.fragments.FragmentList
import company.locahost.itsc.dogetracker.fragments.FragmentMap
import company.locahost.itsc.dogetracker.login.Signin
import kotlinx.android.synthetic.main.activity_base.*


class BaseActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener, View.OnClickListener {
    private lateinit var signin: Signin

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val TAG = "BaseActivity"


    private lateinit var tvUser: TextView

    private  var locationUpdate: Boolean = false

    private  var oldLocation: Location? = null
    private  var newLocation: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)


        bt_list.setOnClickListener(this)
        bt_map.setOnClickListener(this)
        bt_forum.setOnClickListener(this) // TestActivity

        signin = Signin()
        tvUser = findViewById(R.id.tv_user)
        ConstantsDT.userAuth = FirebaseAuth.getInstance()
        createFragment(FragmentMap.TAG)


        checkForLocation()

    }
    /* private fun databaseChecker(){
        if()

    }*/
    @SuppressLint("MissingPermission")
    private fun checkForLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)



        if (newLocation == null) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->

                    newLocation = location

                }
        }
        else{}

    }


    private fun createFragment(tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        when (tag) {
            FragmentMap.TAG -> {
                transaction.replace(R.id.fragment_layout, FragmentMap())

            }
            FragmentList.TAG -> {
                transaction.replace(R.id.fragment_layout, FragmentList())

            }
        }


        transaction.commit()
    }


    override fun onStart() {
        super.onStart()
        checkCurrentUser()

    }

    fun showPopup(v: View) {
        var popup = PopupMenu(this, v)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.base_menu)
        popup.show()
    }

    private fun checkCurrentUser() {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            val uid = user.uid
        }
        if (user != null) {
            Log.i(TAG, "User is signed In " + user.displayName)
            if (user.email == null) ConstantsDT.userEmail = "Anonym"
            else ConstantsDT.userEmail = "" + user.email
            if (user.displayName == null) ConstantsDT.userName = "Anonym"
            else ConstantsDT.userName= "" + user.displayName
            tvUser.setText(ConstantsDT.userEmail)



        } else {
            startActivity(Intent(this, Signin::class.java))
        }
    }
    fun createDog(){
        startActivity(Intent(this@BaseActivity, NewDog::class.java))
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.mn_logout -> {
                    ConstantsDT.userAuth.signOut()
                    checkCurrentUser()
                    return true
                }

                else -> return false
            }
        } else
            return false
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.bt_map -> createFragment(FragmentMap.TAG)
            R.id.bt_list -> createFragment(FragmentList.TAG)
            R.id.bt_forum -> startActivity(Intent(this@BaseActivity, TestActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume started")
    }

}



