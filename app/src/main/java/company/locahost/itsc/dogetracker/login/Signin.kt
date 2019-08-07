package company.locahost.itsc.dogetracker.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import company.locahost.itsc.dogetracker.BaseActivity
import company.locahost.itsc.dogetracker.ConstantsDT
import company.locahost.itsc.dogetracker.MainActivity
import company.locahost.itsc.dogetracker.R
import company.locahost.itsc.dogetracker.profile.createUserData


class Signin : AppCompatActivity(), View.OnClickListener {

    private lateinit var googleSignInClient: GoogleSignInClient


    private lateinit var tvInfo: TextView

    private var bool: Boolean = false

    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRepeat: EditText

    private lateinit var btSubmit: Button
    private lateinit var btBack: Button
    private lateinit var btAnon: Button
    private lateinit var btCreateAc: Button
    private lateinit var signInButton: SignInButton //google button
    private lateinit var btFace: LoginButton //facebook button


    private lateinit var email: String
    private lateinit var password: String
    private lateinit var repPassword: String
    private var user: FirebaseUser? = null

    private lateinit var callbackManager :CallbackManager

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

        //edit text
        etLogin = findViewById(R.id.et_log)
        etPassword = findViewById(R.id.et_pass)
        etRepeat = findViewById(R.id.et_rpass)
        tvInfo = findViewById(R.id.tv_info)

        // buttons
        signInButton = findViewById(R.id.bt_signin_google)
        btSubmit = findViewById(R.id.bt_submit)
        btBack = findViewById(R.id.bt_back)
        btCreateAc = findViewById(R.id.bt_createAc)
        btAnon = findViewById(R.id.bt_anon)
        btFace = findViewById(R.id.login_button)

        // buttons on click .. "this -> method line:~300+ onCLick() "
        btCreateAc.setOnClickListener(this)
        btBack.setOnClickListener(this)
        btSubmit.setOnClickListener(this)
        btAnon.setOnClickListener(this)
        signInButton.setOnClickListener(this)

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        // FIREBASE
        mAuth = FirebaseAuth.getInstance()



        // -----------FACEBOOK------------
        callbackManager = CallbackManager.Factory.create()
        //facebook button action
        btFace.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        })
    }
    override fun onStart() {
        super.onStart()

    }

    private fun passwordVerification():Boolean{
        if (password == repPassword){
            Log.i(TAG, "Passwords are matching")
            return true
        }
        else {
            Log.i(TAG, "Password aren't matching")
            if (!tvInfo.isVisible)
                tvInfo.visibility = View.VISIBLE
            tvInfo.setText("Passwords aren't matching")
            return false
        }
    }
    private fun createAccount(){
        if(!etRepeat.isVisible || bool==true) {
            etRepeat.visibility = View.VISIBLE
            btCreateAc.setText("Sign In With Email")

        }
        else{
            etRepeat.visibility = View.GONE
            btCreateAc.setText("Create Account")

        }
    }
    private fun submit(){
        if(etRepeat.isVisible){
            craftUserWithEmail()
        }
        else{
            signInWithEmail()
        }
    }
    //USER CHECKER ---------------------------------------->


    //BASE ACTIVITY STARTER ----------------------------------------------->
    private fun startBaseActivity(){

        val intent = Intent(this,BaseActivity::class.java)
        ConstantsDT.userAuth = mAuth
        createUserData(mAuth.uid)
        startActivity(intent)

    }

    //AUTHENTICATION METHODS -------------------------------------------------------->
    //---------------------------------------------------------------------facebook auth
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        // [START_EXCLUDE silent]

        // [END_EXCLUDE]

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    startBaseActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }

                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
    }


    //-------------------------------------------Custon Auth
    private fun craftUserWithEmail(){
        email= etLogin.text.toString()
        password = etPassword.text.toString()
        repPassword = etRepeat.text.toString()
        if (passwordVerification()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        startBaseActivity()
                        // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        if (!tvInfo.isVisible)
                        tvInfo.visibility = View.VISIBLE
                        tvInfo.setText(""+task.exception)

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }

                    // ...
                }

        }
    }
    private fun signInWithEmail(){
        email= etLogin.text.toString()
        password = etPassword.text.toString()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this,"SignIn succesfull",Toast.LENGTH_SHORT).show()
                    startBaseActivity()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    }
    //--------------------------------------------------------- Google auth
    private fun signInWithGoogle() {
          val signInIntent = googleSignInClient.signInIntent
          startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
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
                    startBaseActivity()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }


                // [END_EXCLUDE]
            }
    }
    // ---------------------------------------- Anonym auth
    private fun signinAnonym(){
    mAuth.signInAnonymously()
    .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "signInAnonymously:success")
            user = mAuth.currentUser
            Toast.makeText(baseContext,"SignIn Was succesful",Toast.LENGTH_SHORT).show()
            startBaseActivity()

        } else {
            // If sign in fails, display a message to the user.
            Log.w(TAG, "signInAnonymously:failure", task.exception)
            Toast.makeText(
                baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

        // ...
    }



    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data);

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
    //on click Listener
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.bt_signin_google -> signInWithGoogle()
            R.id.bt_submit -> submit()
            R.id.bt_back -> startActivity(Intent(this, MainActivity::class.java))
            R.id.bt_anon -> signinAnonym()
            R.id.bt_createAc -> createAccount()

        }
    }






}
