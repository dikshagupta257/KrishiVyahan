package com.codingblocksmodules.agrome.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codingblocksmodules.agrome.databinding.ActivitySignInBinding
import com.codingblocksmodules.agrome.util.Preferences
import com.codingblocksmodules.agrome.util.CustomToast
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private lateinit var firebaseDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        //setting up custom toolbar with no title on toolbar
        setSupportActionBar(binding.toolbar)
        title = ""

        //Process to login the user one the Log In button is clicked
        binding.signInButton.setOnClickListener {
            val phoneNo = binding.editTextPhoneNumberSignIn.text.toString()
            val password = binding.editTextPasswordSignIn.text.toString()

            //various checks for edge cases to login the user
            if(phoneNo.isEmpty()){

                binding.editTextPhoneNumberSignIn.error = "Please provide your Phone number"
                binding.editTextPhoneNumberSignIn.requestFocus()
            }
            else if(password.isEmpty()){

                binding.editTextPasswordSignIn.error = "Please provide password"
                binding.editTextPasswordSignIn.requestFocus()

            }
            else if(phoneNo.isEmpty() && password.isEmpty()){

                Toast.makeText(this,"Fields are empty" , Toast.LENGTH_SHORT).show()

            }
            else if(!(phoneNo.isEmpty() && password.isEmpty())) {

                //process to login the user according to type of user (seller/consumer)
                firebaseDatabaseReference = FirebaseDatabase.getInstance().reference
                firebaseDatabaseReference.child("Login")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.child(phoneNo).exists()) {

                                if (snapshot.child(phoneNo).child("password").getValue(String::class.java).equals(password)) {
                                    //if user toggles on the remember me switch
                                    if (binding.rememberMe.isChecked) {

                                        if (snapshot.child(phoneNo).child("type").getValue(String::class.java).equals("seller")) {
                                            //to check if the current user is a seller
                                            Preferences.setDataLogin(this@SignInActivity, true)
                                            Preferences.setDataAs(this@SignInActivity, "seller")
                                            Preferences.setUserNumber(this@SignInActivity,phoneNo)
                                            CustomToast().createToast(this@SignInActivity,"Logged In Successfully.", false)
                                            val intent = Intent(
                                                this@SignInActivity,
                                                SellerActivity::class.java
                                            )
                                            intent.putExtra("Number",phoneNo)
                                            startActivity(
                                                intent
                                            )
                                            finish()

                                        } else if (snapshot.child(phoneNo).child("type").getValue(String::class.java).equals("consumer")) {
                                            //to check if the current user is a consumer
                                            Preferences.setDataLogin(this@SignInActivity, true)
                                            Preferences.setDataAs(this@SignInActivity, "consumer")
                                            Preferences.setUserNumber(this@SignInActivity,phoneNo)
                                            CustomToast().createToast(this@SignInActivity,"Logged In Successfully.", false)
                                            val intent = Intent(
                                                this@SignInActivity,
                                                ConsumerActivity::class.java
                                            )
                                            intent.putExtra("Number",phoneNo)
                                            startActivity(
                                                intent
                                            )
                                            finish()
                                        }
                                    }
                                    //if the user has toggled off the remember me switch
                                    else {
                                        if (snapshot.child(phoneNo).child("type").getValue(String::class.java).equals("seller")) {
                                            //to check if the current user is a seller
                                            Preferences.setDataLogin(this@SignInActivity, false)
                                            CustomToast().createToast(this@SignInActivity,"Logged In Successfully.", false)
                                            val intent = Intent(
                                                this@SignInActivity,
                                                SellerActivity::class.java
                                            )
                                            intent.putExtra("Number",phoneNo)
                                            startActivity(
                                                intent
                                            )
                                            finish()
                                        } else if (snapshot.child(phoneNo).child("type").getValue(String::class.java).equals("consumer")) {
                                            //to check if the current user is a consumer
                                            Preferences.setDataLogin(this@SignInActivity, false)
                                            CustomToast().createToast(this@SignInActivity,"Logged In Successfully.", false)
                                            val intent = Intent(
                                                this@SignInActivity,
                                                ConsumerActivity::class.java
                                            )
                                            intent.putExtra("Number",phoneNo)
                                            startActivity(
                                                intent
                                            )
                                            finish()
                                        }

                                    }
                                } else {
                                    //Incorrect Password entered
                                    CustomToast().createToast(this@SignInActivity,"Incorrect Password",true)
                                }
                            } else {
                                //current user not registered
                                CustomToast().createToast(
                                    this@SignInActivity,
                                    "Please register first.",
                                    true
                                )
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })

            }
        }

        //setting up intent to register activity
        binding.textViewSignUp.setOnClickListener {
            val i = Intent(this , SignUpActivity::class.java)
            startActivity(i)
        }

    }

    //logging in the user as per whether remember me was clicked or not while logging in for the first time
    override fun onStart() {
        super.onStart()
        if(Preferences.getDataLogin(this)){
            if(Preferences.getDataAs(this).equals("seller")){
                val phoneNumber = Preferences.getUserNumber(this)
                val intent = Intent(
                    this@SignInActivity,
                    SellerActivity::class.java
                )
                intent.putExtra("Number", phoneNumber)
                startActivity(
                    intent
                )
                finish()

            }else if(Preferences.getDataAs(this).equals("consumer")){
                val phoneNumber = Preferences.getUserNumber(this)
                val intent = Intent(
                    this@SignInActivity,
                    ConsumerActivity::class.java
                )
                intent.putExtra("Number", phoneNumber)
                startActivity(
                    intent
                )
                finish()

            }
        }
    }
}