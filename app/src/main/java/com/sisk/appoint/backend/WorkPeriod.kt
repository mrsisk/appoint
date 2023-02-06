package com.sisk.appoint.backend

import com.sisk.appoint.model.Period
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class WorkPeriod(val start: LocalTime = LocalTime.now(), val end: LocalTime = LocalTime.now(), val session: String = "Morning"): Comparable<WorkPeriod>{
    override fun compareTo(other: WorkPeriod): Int {
        return  start.compareTo(other.start)
    }
}

fun WorkPeriod.toPeriod(formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")): Period{
    val startTime = start.format(formatter)
    val endTime = end.format(formatter)
    return Period(start = startTime, end = endTime, session = session)
}