package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R

class RecommendedCropsAdapter(private val list:List<String>):RecyclerView.Adapter<RecommendedCropsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedCropsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommended_crop, parent, false)
        return RecommendedCropsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendedCropsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
class RecommendedCropsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bind(item:String) = with(itemView){
        val cropName = findViewById<TextView>(R.id.tvCropName)
        cropName.text = item
    }

}