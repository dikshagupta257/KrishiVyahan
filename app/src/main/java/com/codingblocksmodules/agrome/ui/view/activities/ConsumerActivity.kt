package com.codingblocksmodules.agrome.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.UserDetails
import com.codingblocksmodules.agrome.databinding.ActivityConsumerBinding
import com.codingblocksmodules.agrome.util.Preferences
import com.codingblocksmodules.agrome.util.WifiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class ConsumerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsumerBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var phoneNo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupServices()

        //setting up the navigation architecture with drawer layout
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        drawerLayout = findViewById(R.id.drawerLayout)
        binding.navView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //getting the logged in user using intent extra from login/register activity
        phoneNo = intent.getStringExtra("Number")!!

        binding.navView.menu.findItem(R.id.nav_logOut).setOnMenuItemClickListener {
            logout()
            true
        }

        //setting up the header of drawer layout
        FirebaseDatabase.getInstance().reference.child("Login").child(phoneNo).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(UserDetails::class.java)

                //getting header layout id from navigation view
                val header = binding.navView.getHeaderView(0)
                val userProfileImage = header.findViewById<ImageView>(R.id.userProfileImage)
                val userName = header.findViewById<TextView>(R.id.userName)
                val userEmail = header.findViewById<TextView>(R.id.userEmail)

                if (user?.profilePic?.isNotEmpty() == true) {
                    Picasso.get().load(user.profilePic).placeholder(R.drawable.defaultavatar)
                        .error(R.drawable.defaultavatar)
                        .into(userProfileImage)
                }
                userName.text = user?.name
                userEmail.text = user?.email
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    //setting up wifi service in order to check availability of internet for making API Connection
    private fun setupServices() {
        WifiService.instance.initializeWithApplicationContext(this)
    }

    //to logout of the app
    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val i = Intent(this, SignInActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        Preferences.clearData(this)
    }

    //handling the back press feature for opening/closing the drawer
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //inflating the menu on toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    //setting up the actions of menu options on toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.myProfile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("Number",phoneNo)
                startActivity(intent)
                return true
            }

            R.id.shop -> {
                val intent = Intent(this, ShopActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //again setting up the header once the activity is in resume state
    override fun onResume() {
        super.onResume()
        FirebaseDatabase.getInstance().reference.child("Login").child(phoneNo).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(UserDetails::class.java)
                val header = binding.navView.getHeaderView(0)
                val userProfileImage = header.findViewById<ImageView>(R.id.userProfileImage)
                val userName = header.findViewById<TextView>(R.id.userName)
                val userEmail = header.findViewById<TextView>(R.id.userEmail)

                if (user?.profilePic?.isNotEmpty() == true) {
                    Picasso.get().load(user.profilePic).placeholder(R.drawable.defaultavatar)
                        .error(R.drawable.defaultavatar)
                        .into(userProfileImage)
                }
                userName.text = user?.name
                userEmail.text = user?.email
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}