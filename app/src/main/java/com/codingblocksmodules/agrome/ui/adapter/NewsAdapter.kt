package com.codingblocksmodules.agrome.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.NewsItem


class NewsAdapter(private val list:List<NewsItem>):RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

@SuppressLint("SetJavaScriptEnabled")
class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    private val  webView: WebView = itemView.findViewById(R.id.video_view)

    fun bind(newsItem: NewsItem) = with(itemView){
        val headline = findViewById<TextView>(R.id.tvHeadline)
        val content = findViewById<TextView>(R.id.tvContent)

        headline.text = newsItem.headline
        content.text = newsItem.content
        webView.loadUrl(newsItem.ytVideoLink)
    }
    init{
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
    }
}


