package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.RecentNewsItem

class RecentNewsAdapter(private val list:List<RecentNewsItem>) : RecyclerView.Adapter<RecentNewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentNewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_news,parent, false)
        return RecentNewsHolder(view)
    }

    override fun onBindViewHolder(holder: RecentNewsHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class RecentNewsHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bind(recentNewsItem: RecentNewsItem) = with(itemView){
        val ivNews = findViewById<ImageView>(R.id.ivNews)
        val tvHeadline = findViewById<TextView>(R.id.tvHeadline)
        val tvDate = findViewById<TextView>(R.id.tvDate)

        ivNews.setImageResource(recentNewsItem.imageId)
        tvHeadline.text = recentNewsItem.newsHeadline
        tvDate.text = recentNewsItem.date
    }
}
