package company.locahost.itsc.dogetracker

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object ConstantsDT {
    var USER: FirebaseUser? = null
    lateinit var userName: String
    lateinit var userEmail: String
    lateinit var userAuth: FirebaseAuth
    val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    val ERROR_DIALOG_REQUEST = 9000
    val PERMISSIONS_REQUEST_ENABLE_GPS = 9001
    val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9002
}