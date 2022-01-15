package com.codingblocksmodules.agrome.ui.view.activities

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.codingblocksmodules.agrome.databinding.ActivityEditProfileBinding
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

class EditProfileActivity : AppCompatActivity() {
    private lateinit var number:String
    private lateinit var binding:ActivityEditProfileBinding

    private val storage by lazy {
        FirebaseStorage.getInstance()
    }

    private val dbRef by lazy {
      FirebaseDatabase.getInstance().reference
    }

    private lateinit var imageUrl:String

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Edit Profile"

        number = intent.getStringExtra("Number")!!

        binding.changeUserProfile.setOnClickListener {
            checkPermissionForImage()
        }

        binding.btnSaveInformation.setOnClickListener {
            val name = binding.userName.text.toString()
            val address = binding.userAddress.text.toString()
            val country = binding.userCountry.text.toString()
            val email = binding.userEmail.text.toString()

            if(name.isNotEmpty()){
                dbRef.child("Login").child(number).child("name").setValue(name)
            }
            if(address.isNotEmpty()){
                dbRef.child("Login").child(number).child("address").setValue(address)
            }
            if(country.isNotEmpty()){
                dbRef.child("Login").child(number).child("country").setValue(country)
            }
            if(email.isNotEmpty()){
                dbRef.child("Login").child(number).child("email").setValue(email)
            }
            if(::imageUrl.isInitialized){
                dbRef.child("Login").child(number).child("profilePic").setValue(imageUrl)
            }
            Toast.makeText(this, "User Information Saved.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("Number", number)
            startActivity(intent)
            finish()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermissionForImage() {
        if((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)&&
            (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
        ){
            val permissionRead = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            val permissionWrite = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            requestPermissions(permissionRead, 1001)

            requestPermissions(permissionWrite, 1002)

        }else{
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(
            intent,
            1000
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 1000){
            data?.data?.let{
                binding.userProfile.setImageURI(it)
                uploadImage(it)
            }
        }
    }

    private fun uploadImage(it: Uri) {
        binding.btnSaveInformation.isEnabled = false
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait till the image is uploading.")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val ref = storage.reference.child("uploads/$number/profile_pic")
        val uploadTask = ref.putFile(it)

        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task->
            if(!task.isSuccessful){
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation ref.downloadUrl
        }).addOnCompleteListener { task ->
            binding.btnSaveInformation.isEnabled = true
            progressDialog.dismiss()
            Toast.makeText(this, "Image Uploaded Successfully!", Toast.LENGTH_SHORT).show()
            if(task.isSuccessful){
                imageUrl = task.result.toString()
                Log.i("URL","downloadUrl: $imageUrl")
            }
        }.addOnFailureListener{
            binding.btnSaveInformation.isEnabled = true
            progressDialog.dismiss()
            Log.d("exception","reason for exception $it")
            Toast.makeText(this, "Uploading image failed", Toast.LENGTH_SHORT).show()
        }

    }
}