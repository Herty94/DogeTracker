package company.locahost.itsc.dogetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import company.locahost.itsc.dogetracker.login.Signin

class BaseActivity : AppCompatActivity() {
    private lateinit var signin: Signin
    private lateinit var btSignOut: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        signin = Signin()
        btSignOut= findViewById(R.id.bt_signout)
        btSignOut.setOnClickListener{FirebaseAuth.getInstance().signOut()}
    }
}
