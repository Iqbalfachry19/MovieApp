package com.example.movieapp.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun dateConverter(date: String): String {
    val instant = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
        .withZone(ZoneId.of(TimeZone.getDefault().id))
    return formatter.format(instant)
}
