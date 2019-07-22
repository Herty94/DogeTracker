package company.locahost.itsc.dogetracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import company.locahost.itsc.dogetracker.login.Signin

class MainActivity : AppCompatActivity() {

    private lateinit var btLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLogin = findViewById(R.id.bt_login)

        btLogin.setOnClickListener (object: View.OnClickListener {
           override fun onClick(view:View){
               val intent = Intent(this@MainActivity, BaseActivity::class.java )

               startActivity(intent)
           }

        })




    }
}
