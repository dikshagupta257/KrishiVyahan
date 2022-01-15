package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.NutrientsItem

class NutrientsAdapter(private val list: List<NutrientsItem>): RecyclerView.Adapter<NutrientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nutrients, parent, false)
        return NutrientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutrientsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class NutrientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(item:NutrientsItem) = with(itemView){
        val nitrogen = findViewById<TextView>(R.id.tvNitrogen)
        val phosphorus = findViewById<TextView>(R.id.tvPhosphorus)
        val potassium = findViewById<TextView>(R.id.tvPotassium)
        val temperature = findViewById<TextView>(R.id.tvTemperature)
        val humidity = findViewById<TextView>(R.id.tvHumidity)
        val ph = findViewById<TextView>(R.id.tvPH)
        val rainfall = findViewById<TextView>(R.id.tvRainfall)

        nitrogen.text = item.N.toString()
        phosphorus.text = item.P.toString()
        potassium.text = item.K.toString()
        temperature.text = item.temperature.toString()
        humidity.text = item.humidity.toString()
        ph.text = item.ph.toString()
        rainfall.text = item.ph.toString()
    }
}
