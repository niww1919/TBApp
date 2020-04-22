package com.example.tbapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DataRecycler
import com.example.tbapp.data.HeaderViewHolder
import com.example.tbapp.data.NewItemViewHolder
import java.util.*
import java.util.zip.Inflater

class NewItemAdapter(private val data: MutableList<DataRecycler>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == 0) HeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
        )
        else
            NewItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
            )
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0
        else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewItemViewHolder) {
            return holder.bind(data[position - 1])
        }

    }

    override fun getItemCount(): Int {
        return data.size + 1 //todo change size
    }
}
