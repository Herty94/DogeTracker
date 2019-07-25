package company.locahost.itsc.dogetracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.firebase.auth.FirebaseAuth
import company.locahost.itsc.dogetracker.fragments.FragmentMap
import company.locahost.itsc.dogetracker.login.Signin
import kotlinx.android.synthetic.main.fragmen_menu_layout.*
import android.content.ComponentName
import androidx.core.app.BundleCompat.getBinder
import android.os.IBinder
import android.content.ServiceConnection
import android.location.Location
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.android.gms.common.data.DataBufferObserver
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import java.util.concurrent.TimeUnit


class BaseActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private lateinit var signin: Signin
    private lateinit var name: String
    private lateinit var mapFragment: MapFragment
    private lateinit var googleMap: GoogleMap
    private val TAG = "BaseActivity"


    private lateinit var tvUser: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)




        signin = Signin()
        tvUser = findViewById(R.id.tv_user)
        ConstantsDT.userAuth = FirebaseAuth.getInstance()
        createFragment()


    }


    private fun createFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val newFragment = FragmentMap()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, newFragment)
        transaction.addToBackStack(null)
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
            tvUser.setText(ConstantsDT.userEmail)


        } else {
            startActivity(Intent(this, Signin::class.java))
        }
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


}

