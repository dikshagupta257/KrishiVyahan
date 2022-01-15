package com.codingblocksmodules.agrome.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.UserDetails
import com.codingblocksmodules.agrome.databinding.ActivityProfileBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProfileBinding
    private lateinit var user:UserDetails

    private val dbRef by lazy{
        FirebaseDatabase.getInstance().reference
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "User Profile"

        val number = intent.getStringExtra("Number")!!

       dbRef.child("Login").child(number).addListenerForSingleValueEvent(object : ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
               user = snapshot.getValue(UserDetails::class.java)!!
               setViews(user)
           }

           override fun onCancelled(error: DatabaseError) {
               TODO("Not yet implemented")
           }

       })

        binding.editBtn.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("Number",number)
            startActivity(intent)
            finish()
        }

    }
    private fun setViews(user:UserDetails){
        if(user.profilePic.isNotEmpty()){
            Picasso.get().load(user.profilePic)
                .placeholder(R.drawable.defaultavatar)
                .error(R.drawable.defaultavatar)
                .into(binding.userImgView)
        }
        binding.tvUserName.text = user.name
        binding.tvUserNo.text = user.phNo.toString()

        if(user.address.isNotEmpty()){
            binding.tvUserAddress.text = user.address
        }

        if(user.country.isNotEmpty()){
            binding.tvUserCountry.text = user.country
        }

        if(user.email.isNotEmpty()){
            binding.tvUserEmail.text = user.email
        }
    }
}