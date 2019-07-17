package company.locahost.itsc.dogetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import company.locahost.itsc.dogetracker.login.Signin

class BaseActivity : AppCompatActivity() {
    private lateinit var btLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        btLogout = findViewById(R.id.bt_logout)
        btLogout.setOnClickListener { }
    }
}
