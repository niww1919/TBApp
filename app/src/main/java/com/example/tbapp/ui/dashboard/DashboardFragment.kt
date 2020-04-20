package com.example.tbapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tbapp.R
import com.example.tbapp.data.YouTubeLinkStore
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        val webView: WebView = root.findViewById(R.id.web_view)
        val webView2: WebView = root.findViewById(R.id.web_view2)
        val webView3: WebView = root.findViewById(R.id.web_view3)
        val dataBase = Firebase.database
        val linkStore = dataBase.getReference("Link")
        val youTubeLinkStore: YouTubeLinkStore = YouTubeLinkStore(mutableListOf(), mutableListOf())


//        btn.setOnClickListener {
//
//            youTubeLinkStore.date.add(
//                SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault()).format(
//                    Date()
//                ))
//            linkStore.setValue(youTubeLinkStore)
//
//            val newWebView: WebView = WebView(root.context)
//            newWebView.settings.javaScriptEnabled = true
//            newWebView.loadUrl("https://www.youtube.com/embed/3RuPQ9VW_x8")
//            llVideo.addView(newWebView)
//
//
//        }

        webView.settings.javaScriptEnabled = true
        webView2.settings.javaScriptEnabled = true
        webView3.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.youtube.com/embed/3RuPQ9VW_x8")
        webView2.loadUrl("https://www.youtube.com/embed/XqPe_iAm8lI")
        webView3.loadUrl("https://www.youtube.com/embed/XqPe_iAm8lI")


        return root


    }
}
