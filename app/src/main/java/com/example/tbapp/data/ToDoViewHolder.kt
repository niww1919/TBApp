package com.example.tbapp.data

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R

open class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(text:String) {
        itemView.findViewById<AppCompatTextView>(R.id.tv_todo).text = text
    }





}