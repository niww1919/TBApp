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
import com.example.tbapp.R
import com.example.tbapp.data.YouTubeLinkStore
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class TrainingFragment : Fragment() { //todo made fragment

    private val dateStore = mutableListOf<String>()

    val TAG = "Logging"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_training, container, false)
//        view.findViewById<AppCompatTextView>(R.id.tvCurrentDay).text
        val ivMinus = root.findViewById<AppCompatImageView>(R.id.ivMinus)
        val ivPlus = root.findViewById<AppCompatImageView>(R.id.ivPlus)
        val tvStart = root.findViewById<AppCompatTextView>(R.id.tv_start)
        val tvStop = root.findViewById<AppCompatTextView>(R.id.tv_stop)
        val tvResult = root.findViewById<AppCompatTextView>(R.id.tv_result)


        val timeStore = mutableListOf<Long>(1)

        ivMinus.setOnClickListener {
            tvStart.text = Date().toString()
            timeStore.add(0, Date().time)
        }

        ivPlus.setOnClickListener {

            tvStop.text = Date().toString()
            calculateTime(timeStore, tvResult)
            loadDataToFireBase(root)
//
//            addToRealTimeFireBase()
        }

        return root
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
        val dataBase = Firebase.database.getReference("Date")

        dateStore.add(
            SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.getDefault()).format(
                Date()
            )
        )

        dataBase.setValue(dateStore)

//        store.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val value = dataSnapshot.getValue<String>()
//                Log.d(TAG, "Value is: $value")
//
//                //todo this is read db
//            }
//        })

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
                Log.d("Realtime db", it.toString())
            }.addOnFailureListener {
                Log.d("Realtime db", it.toString())

            }
    }


}