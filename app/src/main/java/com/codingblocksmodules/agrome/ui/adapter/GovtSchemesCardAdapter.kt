package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.GovtSchemesItems

class GovtSchemesCardAdapter(private val list: List<GovtSchemesItems>): RecyclerView.Adapter<GovtSchemesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GovtSchemesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_govt_scheme,parent,false)
        return GovtSchemesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GovtSchemesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class GovtSchemesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(govtSchemesItems: GovtSchemesItems) = with(itemView){
        val ivScheme = findViewById<ImageView>(R.id.ivScheme)
        val tvScheme = findViewById<TextView>(R.id.tvScheme)

        ivScheme.setImageResource(govtSchemesItems.imageId)
        tvScheme.text = govtSchemesItems.title
    }


}
