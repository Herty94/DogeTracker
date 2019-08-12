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
private var key: String? = null

fun createUserData(userId: String?){



    myRef.child("users").child(userId!!).child("name").setValue(ConstantsDT.userName)

}
fun createDogData(dog: Dog){

    key = myRef.child("user_dogs").push().key
    myRef.child("user_dogs").child(ConstantsDT.userAuth.uid!!).child(key!!).setValue(dog)

}
fun deleteDogData(dog: Dog){

    Log.i(TAG,"Query: "+myRef.child("user_dogs"))
    var query: Query = myRef.child("user_dogs").child(ConstantsDT.userAuth.uid!!)
    query.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (appleSnapshot in dataSnapshot.children){
                Log.i(TAG,"delete: "+dataSnapshot.child(appleSnapshot.key!!).child("name").getValue(true))
                if(dataSnapshot.child(appleSnapshot.key!!).child("name").getValue(true)== dog.getName()){
                    Log.i(TAG,"deleted: " +dog.getName())
                    myRef.child(appleSnapshot.key!!).updateChildren(null)
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.e(TAG, "onCancelled", databaseError.toException())
        }
    })


}

