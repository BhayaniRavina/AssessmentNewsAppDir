package com.example.assessmentnewstest.util

import java.text.SimpleDateFormat
import java.util.*

class Converter {
    companion object{
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("MMMM dd, yyyy HH:mm")
            return format.format(date)
        }
    }
}