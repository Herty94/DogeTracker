package company.locahost.itsc.dogetracker.dogs

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.Task
import company.locahost.itsc.dogetracker.R
import company.locahost.itsc.dogetracker.fragments.FragmentList
import company.locahost.itsc.dogetracker.profile.createDogData
import kotlinx.android.synthetic.main.activity_new_dog.*
import java.io.File
import java.io.FileNotFoundException
import java.util.*
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener





class NewDog : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    private var name: String = ""
    private var breed: String? = null
    private var weight: Double? = null
    private var date: String? = null
    private var notes: String? = null

    private val RESULT_LOAD_IMG: Int = 2205

    private var dayFinal: Int = 0
    private var monthFinal: Int = 0
    private var yearFinal: Int = 0

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    private lateinit var url: String

    private val TAG = "NewDog"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_dog)


        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        et_birth.setOnClickListener { pickDate() }
        bt_newdog.setOnClickListener { createDog() }
        bt_photo.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
        }


    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)


        if (resultCode == Activity.RESULT_OK) {
            try {
                bt_photo.visibility = View.GONE
                val imageUri = data!!.data

                val imageStream = contentResolver.openInputStream(imageUri!!)
                Log.i(TAG, "Image Uri: " + imageUri.lastPathSegment)
                val file = Uri.fromFile(File(imageUri.lastPathSegment))
                val imageRef = storageRef.child("dog_photos/${file.lastPathSegment}")
                val uploadTask = imageRef.putFile(imageUri)
                imageRef.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                    // Got the uri
                    url= uri.toString()
                    // Wrap with Uri.parse() when retrieving
                    Log.i(TAG, "url: " + url )

                }).addOnFailureListener(OnFailureListener {
                    // Handle any errors
                })


                val selectedImage = BitmapFactory.decodeStream(imageStream)
                image_view.setImageBitmap(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        yearFinal = p1
        monthFinal = p2 + 1
        dayFinal = p3

        et_birth.setText("" + "" + dayFinal + "." + monthFinal + "." + yearFinal)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: Int = item.itemId

        if (id == android.R.id.home)
            this.finish()
        return super.onOptionsItemSelected(item)
    }

    private fun createDog() {
        name = et_name.text.toString()
        breed = et_breed.text.toString()
        weight = et_weight.text.toString().toDoubleOrNull()
        notes = et_notes.text.toString()
        date = et_birth.text.toString()

        Log.i(TAG, "date: " + date)


        var dog = Dog(breed, date,url, name, notes, weight)
        createDogData(dog)
        FragmentList.arrayList.add(dog)
        finish()
    }

    private fun pickDate() {

        var c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH) + 1
        day = c.get(Calendar.DAY_OF_MONTH)

        Log.i(TAG, "" + day + "." + month + "." + year)

        val datePicker = DatePickerDialog(this@NewDog, this@NewDog, year, month, day)
        datePicker.show()


    }
}

class Task<T> {

}
