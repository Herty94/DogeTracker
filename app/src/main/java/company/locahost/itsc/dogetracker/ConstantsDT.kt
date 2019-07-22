package company.locahost.itsc.dogetracker

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object ConstantsDT {
    var USER: FirebaseUser? = null
    lateinit var userName: String
    lateinit var userEmail: String
    lateinit var userAuth: FirebaseAuth
}