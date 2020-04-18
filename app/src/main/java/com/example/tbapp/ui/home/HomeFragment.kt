package com.example.tbapp.ui.home

import android.content.Context
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbapp.R
import com.example.tbapp.data.DataRecycler
import com.example.tbapp.data.NewItemRepository
import java.util.*
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

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

        val btn1: AppCompatButton = root.findViewById(R.id.btn1)
        val btn2: AppCompatButton = root.findViewById(R.id.btn2)
        val llc = root.findViewById<LinearLayoutCompat>(R.id.llc)
        val handler = Handler()
        val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_of_day)
        val linearLayoutManager = LinearLayoutManager(root.context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = NewItemAdapter( NewItemRepository.data)



        btn1.setOnClickListener {

            startTimer(handler, root, vibrator, 3000, 2000)
            startTimer(handler, root, vibrator, 6000, 2000)
            startTimer(handler, root, vibrator, 9000, 2000)

        }

        btn2.setOnClickListener {
            //todo how show new fragment
//            val intent = Intent(root.context, TrainingFragment::class.java)
//            startActivity(intent)
//            childFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragment_training,HomeFragment())
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit()

        }
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
