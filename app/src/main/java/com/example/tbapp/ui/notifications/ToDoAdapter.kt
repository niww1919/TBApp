package com.example.tbapp.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.*
import java.util.*
import java.util.zip.Inflater

class ToDoAdapter(private val data: DBToDo) :
    RecyclerView.Adapter<ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {

        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        return holder.bind(data.baseToDo[position])

    }

    override fun getItemCount(): Int {
        return data.baseToDo.size  //todo change size
    }
}
