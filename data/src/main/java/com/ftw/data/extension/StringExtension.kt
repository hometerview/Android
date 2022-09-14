package com.ftw.data.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun String.makeDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.parse(this.substring(0, 10)) as Date
}
