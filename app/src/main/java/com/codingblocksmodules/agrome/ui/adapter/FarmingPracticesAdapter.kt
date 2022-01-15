package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.FarmingPracticeItem

class FarmingPracticesAdapter(val list:List<FarmingPracticeItem>): RecyclerView.Adapter<FarmingPracticesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmingPracticesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farming_practices, parent, false)
        return FarmingPracticesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FarmingPracticesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class FarmingPracticesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    fun bind(farmingPracticeItem: FarmingPracticeItem) = with(itemView){
        val ivPractice = findViewById<ImageView>(R.id.ivPractice)
        val heading = findViewById<TextView>(R.id.practiceHeading)
        val content = findViewById<TextView>(R.id.practiceContent)

        ivPractice.setImageResource(farmingPracticeItem.imageId)
        heading.text = farmingPracticeItem.schemeTitle
        content.text = farmingPracticeItem.schemeContent
    }

}
