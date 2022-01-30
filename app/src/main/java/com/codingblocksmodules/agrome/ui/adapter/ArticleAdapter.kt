package com.codingblocksmodules.agrome.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.Article
import com.squareup.picasso.Picasso

class ArticleAdapter(private val list:List<Article>) : RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(item: Article) = with(itemView){
        val headline = findViewById<TextView>(R.id.tvHeadline)
        val image = findViewById<ImageView>(R.id.ivArticle)
        val clickableText = findViewById<TextView>(R.id.tvClickHere)
        headline.text = item.title
        Picasso.get().load(item.articleImage).into(image)

        //setting up the on click listeners to direct the user on article if headline or text is clicked
        headline.setOnClickListener {
            val url = item.link
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
        clickableText.setOnClickListener{
            val url = item.link
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }

}