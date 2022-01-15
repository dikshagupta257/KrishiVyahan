package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.ShopItem
import com.squareup.picasso.Picasso

class ShopAdapter(private val list: List<ShopItem>): RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class ShopViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    fun bind(shopItem: ShopItem) = with(itemView){
        findViewById<TextView>(R.id.tvItemName).text = shopItem.itemName
        findViewById<TextView>(R.id.tvItemPrice).text = shopItem.price
        Picasso.get().load(shopItem.itemImage).into(findViewById<ImageView>(R.id.imgShopItem))
    }
}
