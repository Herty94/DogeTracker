package company.locahost.itsc.dogetracker.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import company.locahost.itsc.dogetracker.R


abstract class Signin : AppCompatActivity(), View.OnClickListener {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var signInButton: SignInButton
    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRepeat: EditText
    private lateinit var btSubmit: Button
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var repPassword: String
    private var user: FirebaseUser? = null

    private val GOOGLE_SIGN_IN = 9001

    private val TAG = "Signin"

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        etLogin = findViewById(R.id.et_log)
        etPassword = findViewById(R.id.et_pass)
        etRepeat = findViewById(R.id.et_rpass)
        signInButton = findViewById(R.id.b_signin_google)
        btSubmit = findViewById(R.id.bt_submit)
        btSubmit.setOnClickListener(this)
        signInButton.setOnClickListener(this)
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        CheckCurrentUser()
    }
    private fun CheckCurrentUser(){

        user = mAuth.currentUser
        if (user != null){
            Log.i(TAG,"User is signed In")

        }else{
            Log.i(TAG,"No user is signed In")
            TODO("dokončiť uvodnú obrazovku")
        }
    }
    private fun craftUserWithEmail(){
        if (passwordVerification()) {

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth.currentUser
                        // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }

                    // ...
                }

        }


    }
    private fun passwordVerification():Boolean{
        if (password == repPassword){
            Log.i(TAG, "Passwords are matching")
            return true
        }
        else {
            Toast.makeText(baseContext,"Passwords aren't matching",Toast.LENGTH_SHORT).show()
            Log.i(TAG, "Password aren't matching")
            return false
        }
    }
    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        }
    }
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        // [START_EXCLUDE silent]

        // [END_EXCLUDE]

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    user = mAuth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }


                // [END_EXCLUDE]
            }
    }
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.bt_signin_google -> signInWithGoogle()
            R.id.bt_submit -> craftUserWithEmail()
            //R.id.login_button -> fb authentification
        }
    }


}
