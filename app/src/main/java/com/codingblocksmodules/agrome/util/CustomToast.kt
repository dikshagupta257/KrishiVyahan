package com.codingblocksmodules.agrome.util

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.codingblocksmodules.agrome.R

class CustomToast {
    fun createToast(context: Context, message:String, error:Boolean ) {
        val toast = Toast(context)
        val view:View = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)
        val toastTextView = view.findViewById<TextView>(R.id.tvCustomToast)

        val spannableString = SpannableString(message)
        spannableString.setSpan(StyleSpan(Typeface.BOLD),0,spannableString.length, 0)
        spannableString.setSpan(StyleSpan(Typeface.ITALIC),0,spannableString.length, 0)

        toastTextView.text = spannableString
        toast.view = view
        toast.duration = Toast.LENGTH_SHORT

        if(error){
            toastTextView.setTextColor(Color.parseColor("#830300"))
        }else{
            toastTextView.setTextColor(Color.parseColor("#149414"))
        }

        toast.setGravity(Gravity.BOTTOM, 34,300)
        toast.show()
    }
}