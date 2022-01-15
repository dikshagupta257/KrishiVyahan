package com.codingblocksmodules.agrome.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.databinding.ActivitySignUpBinding
import com.codingblocksmodules.agrome.data.model.UserDetails
import com.codingblocksmodules.agrome.util.CustomToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val root = binding.root

        setContentView(root)

        //setting up custom toolbar with no title on toolbar
        setSupportActionBar(binding.toolbar)
        title = ""

        mFirebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabaseReference = FirebaseDatabase.getInstance().reference

        //Process to register the user one the register button is clicked
        binding.signUpButton.setOnClickListener {
            binding.apply {
                val name = binding.editTextName.text.toString()
                val password = binding.editTextPassword.text.toString()
                val phoneNo = binding.editTextPhoneNumber.text.toString()
                val email = binding.editTextEmail.text.toString()
                val type: String = when (rgSellerConsumer.checkedRadioButtonId) {
                    R.id.rbConsumer ->
                        "consumer"
                    R.id.rbSeller ->
                        "seller"
                    else ->
                        "none"
                }
                //various checks for edge cases to login the user
                if (name.isEmpty()) {

                    editTextName.error = "Please enter the name"
                    editTextName.requestFocus()

                } else if (phoneNo.isEmpty()) {

                    editTextPhoneNumber.error = "Please provide your phone number"
                    editTextPhoneNumber.requestFocus()

                }else if (password.isEmpty()) {

                    editTextPassword.error = "Please enter the password"
                    editTextPassword.requestFocus()

                } else if (type == "none") {

                    rbSeller.error = "Please specify the type of user"
                    rgSellerConsumer.requestFocus()

                } else if (!(phoneNo.isEmpty() && password.isEmpty())) {
                    //process to register the user according to type of user (seller/consumer)

                    val phnEmail = "$phoneNo@gmail.com" //creating email with the phone number of user for firebase authentication
                    mFirebaseAuth.createUserWithEmailAndPassword(phnEmail, password) //authenticating with email and password
                        .addOnCompleteListener(this@SignUpActivity) {
                            if (!it.isSuccessful) {
                                //some error occurred while registration
                                CustomToast().createToast(
                                    this@SignUpActivity,
                                    "SignUp Unsuccessful, Please Try Again" + it.exception?.message?.replace("email address","phone number"),
                                    true
                                )
                            } else {
                                //creating the object of user details model
                                val userDetail = UserDetails(name, phoneNo.toLong(), password, email, type)
                                val uid = it.result?.user?.uid

                                if (uid != null) {
                                    firebaseDatabaseReference.child("Login").child(phoneNo).setValue(userDetail)
                                        .addOnSuccessListener {
                                            //once the user is registered properly using authentication
                                            if (type == "seller") {

                                                //if the current user is a seller, move to seller activity
                                                val intent = Intent(this@SignUpActivity, SellerActivity::class.java)
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                intent.putExtra("Number",phoneNo)
                                                CustomToast().createToast(this@SignUpActivity,"Registered Successfully.", false)
                                                startActivity(intent)

                                            } else if (type == "consumer") {

                                                //if the current user is a consumer, move to consumer activity
                                                val intent = Intent(this@SignUpActivity, ConsumerActivity::class.java)
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                intent.putExtra("Number",phoneNo)
                                                CustomToast().createToast(this@SignUpActivity,"Registered Successfully.", false)
                                                startActivity(intent)
                                            }
                                        }
                                }
                            }
                        }
                } else {
                    //if some error occurs while registering the user
                    CustomToast().createToast(this@SignUpActivity, "Error Occurred!", true)
                }
            }
        }

        //setting up intent to login activity
        binding.textViewSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}