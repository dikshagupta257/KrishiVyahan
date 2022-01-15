package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.SellerItem
import com.squareup.picasso.Picasso
import java.util.*

class SellerAdapter(private val list:List<SellerItem>) : RecyclerView.Adapter<SellerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_seller, parent, false)
        return SellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

class SellerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bind(sellerItem:SellerItem) = with(itemView){
        val colors = context.resources.getIntArray(R.array.random_color)
        val randomColor = colors[Random().nextInt(colors.size)]

        val mainBackground = findViewById<LinearLayout>(R.id.llMainBg)
        val sellerProfile = findViewById<ImageView>(R.id.ivSellerProfile)
        val sellerName = findViewById<TextView>(R.id.tvSellerName)
        val itemName = findViewById<TextView>(R.id.tvItemName)
        val quantity = findViewById<TextView>(R.id.tvQuantity)
        val contact = findViewById<TextView>(R.id.tvContact)
        val location = findViewById<TextView>(R.id.tvLocation)
        val itemPriceWithoutTransportation = findViewById<TextView>(R.id.tvItemPriceWithoutTransportation)
        val itemPriceWithTransportation = findViewById<TextView>(R.id.tvItemPriceWithTransportation)

        Picasso.get().load(sellerItem.profileImage).placeholder(R.drawable.defaultavatar)
            .error(R.drawable.defaultavatar)
            .into(sellerProfile)

        mainBackground.setBackgroundColor(randomColor)
        sellerName.text = sellerItem.sellerName
        itemName.text = sellerItem.itemName
        quantity.text = sellerItem.quantity.toString() + " kg"
        contact.text = sellerItem.contact
        location.text = "${sellerItem.district}, ${sellerItem.state}, ${sellerItem.country}"
        itemPriceWithTransportation.text = sellerItem.priceWithTransportation+" Rs."
        itemPriceWithoutTransportation.text = sellerItem.price + " Rs."
    }

}
