package com.example.tbapp.ui.fragment.training

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DBToDo
import com.example.tbapp.data.YouTubeLinkStore
import com.example.tbapp.ui.home.NewItemAdapter
import com.example.tbapp.ui.notifications.ToDoAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ToDoFragment : Fragment() { //todo made fragment

    val db = DBToDo(mutableListOf("A", "B"))


    val TAG = "todolog"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_todo, container, false)

        val btnAddTodo = root.findViewById<AppCompatButton>(R.id.btn_add_todo)
        val rvToDO = root.findViewById<RecyclerView>(R.id.rv_todo)
        val linearLayoutManager = LinearLayoutManager(root.context)
        rvToDO.layoutManager = linearLayoutManager
        rvToDO.adapter = ToDoAdapter(db)

        btnAddTodo.setOnClickListener {
            addToRealTimeFireBase(root)
        }

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {

                    Toast.makeText(root.context, "LEFT", Toast.LENGTH_SHORT).show()

                    addToRealTimeFireBase(root)
                }

                if (direction == ItemTouchHelper.RIGHT) {
                    Toast.makeText(root.context, "RIGHT", Toast.LENGTH_SHORT).show()

                }
            }
        })

        itemTouchHelper.attachToRecyclerView(rvToDO)

        return root
    }

    private fun addToRealTimeFireBase(root: View) {
        val dataBase = Firebase.database
        var text = root.findViewById<AppCompatEditText>(R.id.et_add_todo).text.toString()
        val store =
            dataBase.getReference( text)

        db.baseToDo.add(text)

        store.setValue(db)

        store.child(text).addListenerForSingleValueEvent(object : ValueEventListener {
//        store.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(root.context, "Error read data", Toast.LENGTH_SHORT).show()

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.childrenCount
                Log.d(TAG, "Value is: ${value}")
                Toast.makeText(root.context, "Value is: ${value}", Toast.LENGTH_SHORT).show()

                //todo this is read db
            }
        })

        Toast.makeText(root.context, "Add $text", Toast.LENGTH_SHORT).show()


    }


}