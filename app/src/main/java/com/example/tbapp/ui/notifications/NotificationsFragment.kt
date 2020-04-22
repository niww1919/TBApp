package com.example.tbapp.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DataRecycler
import com.example.tbapp.data.YouTubeLinkStore
import com.example.tbapp.ui.fragment.training.ToDoListFragment
import com.example.tbapp.ui.fragment.training.TrainingFragment
import com.example.tbapp.ui.home.NewItemAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.seconds

class NotificationsFragment : Fragment() {
    val TAG = "Logging"

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val btnToDo = root.findViewById<AppCompatButton>(R.id.btn_open_todo)
        val dbToDO = mutableListOf<DataRecycler>(DataRecycler("!","!"))

        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_todo_list)
        val linearLayoutManager = LinearLayoutManager(root.context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = NewItemAdapter(dbToDO)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                root.context,
                DividerItemDecoration.VERTICAL
            )
        )

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

                    Toast.makeText(root.context, "Swipe", Toast.LENGTH_SHORT).show()
                    dbToDO.removeAt(viewHolder.adapterPosition)
                    (recyclerView.adapter as NewItemAdapter).notifyItemRemoved(viewHolder.adapterPosition) // todo how to remove item
                }

                if (direction == ItemTouchHelper.RIGHT) {
                    fragmentManager?.beginTransaction()
                        ?.replace(
                            R.id.fragmentToDo,
                            TrainingFragment(),
                            TrainingFragment().TAG
                        )
                        ?.addToBackStack(null)
                        ?.commit()
                }


            }

        })
        itemTouchHelper.attachToRecyclerView(recyclerView)


        btnToDo.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragmentContainer,
                    ToDoListFragment(),
                    ToDoListFragment().TAG
                )
                ?.addToBackStack(null)
                ?.commit()
        }

        return root
    }



}
