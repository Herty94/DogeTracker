package company.locahost.itsc.dogetracker

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

        btLogin.findViewById<Button>(R.id.bt_login)

        btLogin.setOnClickListener (object: View.OnClickListener {
           override fun onClick(view:View): Unit{
               val intent = Intent(this@MainActivity, Signin::class.java )
               finish()
               startActivity(intent)
           }

        })




    }
}
