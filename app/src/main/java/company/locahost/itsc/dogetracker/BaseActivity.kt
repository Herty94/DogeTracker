package company.locahost.itsc.dogetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import company.locahost.itsc.dogetracker.login.Signin

class BaseActivity : AppCompatActivity() {
    private lateinit var signin: Signin
    private lateinit var btSignOut: Button
    private lateinit var name: String
    private lateinit var tvUser: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        signin = Signin()
        btSignOut= findViewById(R.id.bt_signout)
        btSignOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,Signin::class.java))
        }
        tvUser = findViewById(R.id.tv_user)
        tvUser.setText(ConstantsDT.userEmail)
    }
}
