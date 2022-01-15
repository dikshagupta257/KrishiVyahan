package com.codingblocksmodules.agrome.ui.view.activities

import android.app.AlertDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codingblocksmodules.agrome.data.model.UserDetails
import com.codingblocksmodules.agrome.data.model.UserPosts
import com.codingblocksmodules.agrome.databinding.ActivityCreatePostBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreatePostBinding
    private lateinit var itemName:String
    private lateinit var qty:String
    private lateinit var district:String
    private lateinit var state:String
    private lateinit var country:String
    private lateinit var contact:String
    private lateinit var pricewt:String
    private lateinit var pricewot:String
    private lateinit var progressDialog: ProgressDialog
    private var name: String? = null
    private var profilePic:String? = null
    private lateinit var number:String

    private val dbRef by lazy{
        FirebaseDatabase.getInstance().reference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        number = intent.getStringExtra("Number")!!

        binding.btnCreate.setOnClickListener {
            itemName = binding.etItemName.text.toString()
            qty = binding.etQuantity.text.toString()
            district = binding.etDistrict.text.toString()
            state = binding.etState.text.toString()
            country = binding.etCountry.text.toString()
            contact = binding.etContact.text.toString()
            pricewt = binding.etPriceWithTransportation.text.toString()
            pricewot = binding.etPriceWithoutTransportation.text.toString()

            when {
                itemName.isEmpty() -> {
                    binding.etItemName.error = "Item Name cannot be empty!"
                    binding.etItemName.requestFocus()
                }
                qty.isEmpty() -> {
                    binding.etQuantity.error = "Quantity cannot be empty!"
                    binding.etQuantity.requestFocus()
                }
                district.isEmpty() -> {
                    binding.etDistrict.error = "District cannot be empty!"
                    binding.etDistrict.requestFocus()
                }
                state.isEmpty() -> {
                    binding.etState.error = "State cannot be empty!"
                    binding.etState.requestFocus()
                }
                country.isEmpty() -> {
                    binding.etCountry.error = "Country cannot be empty!"
                    binding.etCountry.requestFocus()
                }
                contact.isEmpty() -> {
                    binding.etContact.error = "Contact cannot be empty!"
                    binding.etContact.requestFocus()
                }
                pricewt.isEmpty() -> {
                    binding.etPriceWithTransportation.error = "Price cannot be empty!"
                    binding.etPriceWithTransportation.requestFocus()
                }
                pricewot.isEmpty() -> {
                    binding.etPriceWithoutTransportation.error = "Price cannot be empty!"
                    binding.etPriceWithoutTransportation.requestFocus()
                }
                else -> {
                    binding.btnCreate.isEnabled = false

                    progressDialog = ProgressDialog(this)
                    progressDialog.setMessage("Please wait till the post is uploading.")
                    progressDialog.setCancelable(false)
                    progressDialog.show()

                    dbRef.child("Login").child(number).addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(UserDetails::class.java)!!
                            makePost(user)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })


                }
            }
        }
    }

    private fun makePost(user: UserDetails) {
        name = user.name
        profilePic = user.profilePic

        val post =
            UserPosts(name!!, profilePic!!, itemName, qty, district, state, country, contact, pricewt, pricewot)

        val id = dbRef.child("Posts").child(number).push().key!!

        dbRef.child("Posts").child(number).child(id).setValue(post)
            .addOnSuccessListener {
                binding.btnCreate.isEnabled = true
                progressDialog.dismiss()
                Toast.makeText(
                    this,
                    "Post have been successfully uploaded.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()

            }.addOnFailureListener { ex ->
                progressDialog.dismiss()
                AlertDialog.Builder(this)
                    .setMessage("Some error occurred.Please try again.\nError: $ex")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
    }


}