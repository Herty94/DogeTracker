package company.locahost.itsc.dogetracker.profile

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import company.locahost.itsc.dogetracker.ConstantsDT
import company.locahost.itsc.dogetracker.dogs.Dog
import company.locahost.itsc.dogetracker.fragments.FragmentList


private var firebasedatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
private var myRef: DatabaseReference = firebasedatabase.getReference()
private val TAG ="User"

fun createUserData(userId: String?){



    myRef.child("users").child(userId!!).child("name").setValue(ConstantsDT.userName)

}
fun createDogData(dog: Dog){

    val key = myRef.child("user_dogs").push().key
    myRef.child("user_dogs").child(ConstantsDT.userAuth.uid!!).child(key!!).setValue(dog)

}
fun addDogData(){

    val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            for(ds in dataSnapshot.child("user_dogs").child(ConstantsDT.userAuth.uid!!).children){
                Log.i(TAG,"Snapshot: " )
                FragmentList.arrayList.add(ds.getValue(Dog::class.java)!!)

                //FragmentList.arrayList.add(ds.child(ds.key!!).getValue(Dog::class.java)!!)
            }
            FragmentList().createArrayList()

           //
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(FragmentList.TAG, "loadPost:onCancelled", databaseError.toException())
            // ...
        }
    }
        myRef.addValueEventListener(postListener)
}
