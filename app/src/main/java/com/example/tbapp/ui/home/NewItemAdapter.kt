package com.example.tbapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DataRecycler
import com.example.tbapp.data.NewItemViewHolder
import java.util.*
import java.util.zip.Inflater

class NewItemAdapter(private val data:MutableList<DataRecycler>): RecyclerView.Adapter<NewItemViewHolder>() {
//    private val data = mutableListOf(DataRecycler(Date().toString(),"f"), DataRecycler(Date().toString(),"23#@$"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewItemViewHolder {
//        return LayoutInflater.from(parent.context).inflate(R.layout.item_day,parent,false)
        return NewItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_day,parent,false))
    }

    override fun onBindViewHolder(holder: NewItemViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
