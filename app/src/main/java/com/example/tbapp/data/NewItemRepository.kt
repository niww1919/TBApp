package com.example.tbapp.data

import java.text.SimpleDateFormat
import java.util.*

object NewItemRepository {

    val data: MutableList<DataRecycler>
        get() = mutableListOf(
            DataRecycler(
                SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(Date()),
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            ),            DataRecycler(
                SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(Date()),
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            ),            DataRecycler(
                SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(Date()),
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            ),            DataRecycler(
                SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(Date()),
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            ),            DataRecycler(
                SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(Date()),
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            )

            )


}