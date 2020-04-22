package com.example.tbapp.ui.fragment.training

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DataRecycler
import com.example.tbapp.data.YouTubeLinkStore
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

class ToDoListFragment : Fragment() { //todo made fragment


    val TAG = "ToDOList"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_todo_list, container, false)
//        view.findViewById<AppCompatTextView>(R.id.tvCurrentDay).text



        return root
    }

    private fun loadDataToFireBase(root: View) {

        val db = Firebase.firestore

        val time = hashMapOf(
            "Date" to "${Date()}",
            "Time" to "",
            "Type" to ""
        )

        db.collection("time_of_training")
            .add(time)
            .addOnSuccessListener {
                Toast.makeText(
                    root.context,
                    "Time was wrote",
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(
                    root.context,
                    "Time do not was wrote",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun calculateTime(
        timeStore: MutableList<Long>,
        tvResult: AppCompatTextView
    ) {
        timeStore.add(1, Date().time)
        if (timeStore.size == 1) {
            timeStore.add(0, Date().time)
        }
        timeStore.add(2, timeStore[1] - timeStore[0])
        tvResult.text = (timeStore[2] / 1000).toString()
    }

    private fun addToRealTimeFireBase() {
        val dataBase = Firebase.database
        val store = dataBase.getReference(
            SimpleDateFormat("dd_MM_yy HH:mm", Locale.getDefault()).format(
                Date()
            )
        )

        val youTubeLinkStore: YouTubeLinkStore = YouTubeLinkStore(mutableListOf(), mutableListOf())
        var stringStore:String = Date().time.toString()

        youTubeLinkStore.date.add(
            SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(
                Date()
            )
        )

        store.setValue(stringStore)

        store.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")

                //todo this is read db
            }
        })

    }
}