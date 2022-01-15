package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.UserPosts
import com.squareup.picasso.Picasso
import java.util.*

class UserPostAdapter(val list: List<UserPosts>) : RecyclerView.Adapter<UserPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seller, parent, false)
        return UserPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

class UserPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: UserPosts) = with(itemView) {
        val colors = context.resources.getIntArray(R.array.random_color)
        val randomColor = colors[Random().nextInt(colors.size)]

        val mainBackground = findViewById<LinearLayout>(R.id.llMainBg)
        val sellerProfile = findViewById<ImageView>(R.id.ivSellerProfile)
        val sellerName = findViewById<TextView>(R.id.tvSellerName)
        val itemName = findViewById<TextView>(R.id.tvItemName)
        val quantity = findViewById<TextView>(R.id.tvQuantity)
        val contact = findViewById<TextView>(R.id.tvContact)
        val location = findViewById<TextView>(R.id.tvLocation)
        val itemPriceWithoutTransportation =
            findViewById<TextView>(R.id.tvItemPriceWithoutTransportation)
        val itemPriceWithTransportation = findViewById<TextView>(R.id.tvItemPriceWithTransportation)


        if(item.profilePic.isNotEmpty()) {
            Picasso.get().load(item.profilePic).placeholder(R.drawable.defaultavatar)
                .error(R.drawable.defaultavatar)
                .into(sellerProfile)
        }

        sellerName.text = item.name
        mainBackground.setBackgroundColor(randomColor)
        itemName.text = item.itemName
        quantity.text = item.qty + " kg"
        contact.text = item.contact
        location.text = "${item.district}, ${item.state}, ${item.country}"
        itemPriceWithTransportation.text = item.pricewt + " Rs."
        itemPriceWithoutTransportation.text = item.pricewot + " Rs."

    }

}
