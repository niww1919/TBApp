package com.example.tbapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    private val  date = Date()
    private val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"


    private val _text = MutableLiveData<String>().apply {
        value = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(date)

    }
    val text: LiveData<String> = _text
}