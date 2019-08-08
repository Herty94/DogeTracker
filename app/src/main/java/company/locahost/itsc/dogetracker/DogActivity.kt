package company.locahost.itsc.dogetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import company.locahost.itsc.dogetracker.dogs.Dog
import company.locahost.itsc.dogetracker.fragments.FragmentList.Companion.arrayList
import kotlinx.android.synthetic.main.activity_dog.*

class DogActivity : AppCompatActivity() {

    private var i: Int = 0
    private lateinit var dog: Dog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        i = intent.getIntExtra("arrayListId", 0)
        dog = arrayList.get(i)

        dog.getName()

    }

    override fun onResume() {
        super.onResume()
        tv_name.setText(dog.getName())
        tv_breed.setText(dog.getBreed())
        tv_date.setText(dog.getDate())
        tv_note.setText(dog.getNotes())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: Int = item.itemId

        if (id == android.R.id.home)
            this.finish()
        return super.onOptionsItemSelected(item)
    }

}

