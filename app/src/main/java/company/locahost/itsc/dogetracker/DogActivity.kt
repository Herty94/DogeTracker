package company.locahost.itsc.dogetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.net.toUri
import com.squareup.picasso.Picasso
import company.locahost.itsc.dogetracker.dogs.Dog
import company.locahost.itsc.dogetracker.fragments.FragmentList.Companion.arrayList
import company.locahost.itsc.dogetracker.profile.deleteDogData
import kotlinx.android.synthetic.main.activity_dog.*

class DogActivity : AppCompatActivity() , View.OnClickListener{

    private var i: Int = 0
    private lateinit var dog: Dog

    private var edit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        i = intent.getIntExtra("arrayListId", 0)
        dog = arrayList.get(i)
        bt_edit.setOnClickListener(this)
        bt_delete.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        tv_name.setText("Meno: "+dog.getName())
        tv_breed.setText("rasa: "+dog.getBreed())
        tv_date.setText("datum: "+dog.getDate())
        tv_weight.setText("váha: "+dog.getWeight())
        tv_note.setText("popis: "+dog.getNotes())
        Picasso.get().load(dog.getImageUrl()).into(iv_dog)

    }
    private fun updateUI(){

        TODO("dokončiť editovanie psa")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: Int = item.itemId

        if (id == android.R.id.home)
            this.finish()
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(view: View){
        when(view.id){
            R.id.bt_edit -> {
                if(edit){
                    updateUI()
                }
                else TODO("dokončiť editovanie psa")
            }
            R.id.bt_delete -> {
                deleteDogData(dog)
                finish()}
        }
    }

}

