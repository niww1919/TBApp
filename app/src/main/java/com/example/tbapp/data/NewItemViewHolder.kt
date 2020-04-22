package com.example.tbapp.data

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R

open class NewItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(dataRecycler: DataRecycler) {
        itemView.findViewById<AppCompatTextView>(R.id.tv_date_of_training).text = dataRecycler.date
        itemView.findViewById<AppCompatTextView>(R.id.tv_day_of_training).text = dataRecycler.day
    }





}