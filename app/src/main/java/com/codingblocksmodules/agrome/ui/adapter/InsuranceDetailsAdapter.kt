package com.codingblocksmodules.agrome.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.model.InsuranceItem

class InsuranceDetailsAdapter(private val list: List<InsuranceItem>?): RecyclerView.Adapter<InsuranceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsuranceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_insurance_details, parent, false)
        return InsuranceViewHolder(view)
    }

    override fun onBindViewHolder(holder: InsuranceViewHolder, position: Int) {
        holder.bind(list!![position])
        Log.d("api", "onBindViewHolder: $list")
    }

    override fun getItemCount(): Int = list!!.size
}

class InsuranceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(insuranceItem: InsuranceItem) = with(itemView){
        findViewById<TextView>(R.id.tvCompanyName).text = insuranceItem.companyName
        findViewById<TextView>(R.id.tvCompanyCode).text = insuranceItem.companyCode.toString()
        findViewById<TextView>(R.id.tvContact).text = insuranceItem.contactNo
        findViewById<TextView>(R.id.tvEmail).text = insuranceItem.email
        findViewById<TextView>(R.id.tvAddress).text = insuranceItem.address

    }

}
