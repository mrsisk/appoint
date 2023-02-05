package com.sisk.appoint.utils

import com.sisk.appoint.model.AppointDateTime
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toAppointDate(formatter: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault())): AppointDateTime{
    val todayShortDay = getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()) ?: ""
    val todayFull = formatter.format(time)
    val day = get(Calendar.DATE)
    val formattedDay = String.format("%02d", day)
    return AppointDateTime(shortDay = todayShortDay, shortDate =formattedDay, fullDate = todayFull)
}