package com.codingblocksmodules.agrome.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.UserDetails
import com.codingblocksmodules.agrome.data.model.UserPosts
import com.codingblocksmodules.agrome.ui.adapter.UserPostAdapter
import com.codingblocksmodules.agrome.ui.view.activities.SellerActivity
import com.codingblocksmodules.agrome.ui.view.activities.CreatePostActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class MyPostsFragment : Fragment() {
    private val  list = arrayListOf<UserPosts>()
    private val dbRef by lazy {
        FirebaseDatabase.getInstance().reference
    }
    private lateinit var arrayAdapter: UserPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_posts, container, false)

        val btnCreatePost = view.findViewById<MaterialButton>(R.id.btnCreatePost)
        val profile = view.findViewById<ImageView>(R.id.ivUserProfile)
        val name = view.findViewById<TextView>(R.id.tvUserName)
        val email = view.findViewById<TextView>(R.id.tvUserEmail)
        val posts = view.findViewById<RecyclerView>(R.id.rvUserPosts)

        //setting up adapter for posts added by seller
        arrayAdapter = UserPostAdapter(list)
        posts.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, true)
        posts.adapter = arrayAdapter
        posts.isNestedScrollingEnabled = false

        //to get the logged in user's number from the intent extra of seller activity
        val sellerActivity = activity as SellerActivity
        val number = sellerActivity.getData()

        //setting up seller image, name and email id on his post feed fragment
        dbRef.child("Login").child(number).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(UserDetails::class.java)!!
                if(user.profilePic.isNotEmpty()) {
                    Picasso.get().load(user.profilePic)
                        .placeholder(R.drawable.defaultavatar)
                        .error(R.drawable.defaultavatar)
                        .into(profile)
                }

                name.text = user.name
                email.text = user.email
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        btnCreatePost.setOnClickListener {
            val intent = Intent(requireContext(), CreatePostActivity::class.java)
            intent.putExtra("Number", number)
            startActivity(intent)
        }

        //to show the post in this fragment feed posted by this user
        dbRef.child("Posts").child(number).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val temp = ArrayList<UserPosts>()
                for(data:DataSnapshot in snapshot.children){
                    data.getValue(UserPosts::class.java)?.let { temp.add(it) }
                }
                list.clear()
                list.addAll(temp)
                arrayAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                //not needed
            }
        })
        return view
    }

}

