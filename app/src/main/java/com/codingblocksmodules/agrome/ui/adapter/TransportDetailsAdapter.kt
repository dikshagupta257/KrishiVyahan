package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.TransportItem
import com.squareup.picasso.Picasso

class TransportDetailsAdapter(private val list:List<TransportItem>?):RecyclerView.Adapter<TransportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transport_details, parent, false)
        return TransportViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    override fun getItemCount(): Int = list!!.size
}

class TransportViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    fun bind(transportItem: TransportItem) = with(itemView){
        findViewById<TextView>(R.id.tvCompanyName).text = transportItem.companyName
        findViewById<TextView>(R.id.tvCategory).text = transportItem.category
        findViewById<TextView>(R.id.tvAvailability).text = transportItem.availability
        findViewById<TextView>(R.id.tvContact).text = transportItem.contactNo
        findViewById<TextView>(R.id.tvAddress).text = transportItem.address
        //Log.d("transport", "bind:${transportItem.profilePic} ")
        Picasso.get().load(transportItem.profileImage).into(findViewById<ImageView>(R.id.ivTransportImage))
    }
}
