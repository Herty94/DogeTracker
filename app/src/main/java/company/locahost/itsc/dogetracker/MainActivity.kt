package company.locahost.itsc.dogetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var LocationPermission : Boolean = false

    private lateinit var btLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLogin = findViewById(R.id.bt_login)

        btLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@MainActivity, BaseActivity::class.java)
                finish()
                startActivity(intent)
            }

        })


    }
}
