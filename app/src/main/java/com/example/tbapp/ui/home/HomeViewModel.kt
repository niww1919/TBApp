package com.example.tbapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel : ViewModel() {

    var  date = Date()

    private val _text = MutableLiveData<String>().apply {
        value = date.time.toString()
    }
    val text: LiveData<String> = _text
}