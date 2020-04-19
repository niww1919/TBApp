package com.example.tbapp.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.example.tbapp.R
import java.util.*

class TrainingFragment : Fragment() { //todo made fragment


    val TAG  = "Train"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_training, container, false)
//        view.findViewById<AppCompatTextView>(R.id.tvCurrentDay).text
        val btnStart = root.findViewById<AppCompatButton>(R.id.btn_start)
        val btnStop = root.findViewById<AppCompatButton>(R.id.btn_stop)
        val tvStart = root.findViewById<AppCompatTextView>(R.id.tv_start)
        val tvStop = root.findViewById<AppCompatTextView>(R.id.tv_stop)
        val tvResult = root.findViewById<AppCompatTextView>(R.id.tv_result)

        val timeStore = mutableListOf<Long>(1)

        btnStart.setOnClickListener {
            tvStart.text = Date().toString()
            timeStore.add(0, Date().time)
        }

        btnStop.setOnClickListener {
            tvStop.text = Date().toString()
            timeStore.add(1, Date().time)
            if (timeStore.size == 1) {
                timeStore.add(0, Date().time)
            }
            timeStore.add(2, timeStore[1] - timeStore[0])
            tvResult.text = (timeStore[2]/1000).toString()
//                tvResult.text = SimpleDateFormat("mm:ss",Locale.getDefault()).parse(timeStore[2].toString()).toString()
        }

        return root
    }


}