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
import com.codingblocksmodules.agrome.data.model.Video

class VideoAdapter(private val list:List<Video>) : RecyclerView.Adapter<VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

@SuppressLint("SetJavaScriptEnabled")
class VideoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    private val  webView: WebView = itemView.findViewById(R.id.video_view)

    fun bind(item: Video) = with(itemView){
        val headline = findViewById<TextView>(R.id.tvHeadline)
        headline.text = item.title
        val videoLink = item.videoLink?.substring(0,13)+"be.com/embed/"+item.videoLink?.substring(17)
        webView.loadUrl(videoLink)
    }
    init{
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
    }
}
