package com.example.tbapp.ui.home

import android.content.Context
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DataRecycler
import com.example.tbapp.data.NewItemRepository
import com.example.tbapp.ui.fragment.training.TrainingFragment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val itemData: MutableList<DataRecycler> = mutableListOf()
    val TAG = "LOADTIME"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val handler = Handler()
        val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        //todo realtime database
        val database = FirebaseDatabase.getInstance().getReference()
        database.toString()

        //todo firestore
//        val db =FirebaseFirestore.getInstance()
//        val dataMap = HashMap<String,String>()
//        dataMap[homeViewModel.text.toString()] = homeViewModel.text.toString()

//        db.collection("Time")
//            .add(dataMap)
//            .addOnSuccessListener {
//                Log.d(TAG, "DocumentSnapshot added with ID: " + it.getId());
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "Error adding document", it);
//            }


        itemData.addAll(NewItemRepository.data)

        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_of_day)
        val linearLayoutManager = LinearLayoutManager(root.context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = NewItemAdapter(itemData)
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
                    itemData.removeAt(viewHolder.adapterPosition)
                    (recyclerView.adapter as NewItemAdapter).notifyItemRemoved(viewHolder.adapterPosition) // todo how to remove item
                }

                if (direction == ItemTouchHelper.RIGHT) {
                    fragmentManager?.beginTransaction()
                        ?.replace(
                            R.id.fragmentContainer,
                            TrainingFragment(),
                            TrainingFragment().TAG
                        )
                        ?.addToBackStack(null)
                        ?.commit()
                }


            }

        })

        itemTouchHelper.attachToRecyclerView(recyclerView)


//        btn1.setOnClickListener {
//            //todo how show new fragment
//
//            fragmentManager?.beginTransaction()
//                ?.replace(R.id.fragmentContainer, TrainingFragment(), TrainingFragment().TAG)
//                ?.addToBackStack(null)
//                ?.commit()
////            startTimer(handler, root, vibrator, 3000, 2000)
//
//        }

        return root
    }


    private fun startTimer(
        handler: Handler,
        root: View,
        vibrator: Vibrator,
        time: Long,
        timeOfVibrator: Long

    ) {
        handler.post {
            object : CountDownTimer(time, 1000) {

                override fun onFinish() {
                    Toast.makeText(root.context, "Handler", Toast.LENGTH_SHORT).show()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(
                                timeOfVibrator,
                                VibrationEffect.DEFAULT_AMPLITUDE
                            )
                        );
                    } else {
                        //deprecated in API 26
                        vibrator.vibrate(timeOfVibrator)
                    }
                }

                override fun onTick(millisUntilFinished: Long) {
                    Toast.makeText(
                        root.context,
                        "${millisUntilFinished / 1000}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }.start()
        }
    }
}
