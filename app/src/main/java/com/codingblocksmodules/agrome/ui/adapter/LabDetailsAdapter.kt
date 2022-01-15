package com.codingblocksmodules.agrome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.LabItem

class LabDetailsAdapter(private val list:List<LabItem>?) :RecyclerView.Adapter<LabViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lab_details, parent, false)
        return LabViewHolder(view)
    }

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    override fun getItemCount(): Int = list!!.size
}

class LabViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(labItem: LabItem) = with(itemView){
        findViewById<TextView>(R.id.tvLabName).text = labItem.labName
        findViewById<TextView>(R.id.tvTestName).text = labItem.testName
        findViewById<TextView>(R.id.tvTestingAt).text = labItem.testingAt
        findViewById<TextView>(R.id.tvCharges).text = labItem.charges.toString()
        findViewById<TextView>(R.id.tvContact).text = labItem.contactInfo
        findViewById<TextView>(R.id.tvLocation).text = labItem.location

    }
}
