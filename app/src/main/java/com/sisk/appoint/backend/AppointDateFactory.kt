package com.sisk.appoint.backend

import com.sisk.appoint.model.AppointDate
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*

class AppointDateFactory(zoneId: ZoneId) {
    private val now = LocalDate.now(zoneId)

    private val weekendTemporalAdjuster = TemporalAdjuster {
        if (DayOfWeek.from(it) != DayOfWeek.FRIDAY) it.plus(1, ChronoUnit.DAYS) else it.with(
            TemporalAdjusters.next(DayOfWeek.MONDAY)
        )
    }

    fun workingDays(numberOfDays: Int): List<AppointDate>{
        return generateSequence(now){it.with(weekendTemporalAdjuster)}
            .take(numberOfDays)
            .map { it.toAppointDate() }
            .toList()
    }
}

fun LocalDate.toAppointDate(formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")): AppointDate{
    val dayOfWeek = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val fullDate = format(formatter)
    val day = dayOfMonth
    val formattedDay = String.format("%02d", day)
    return AppointDate(dayOfWeek = dayOfWeek, dayOfMonth = formattedDay, fullDate = fullDate)
}