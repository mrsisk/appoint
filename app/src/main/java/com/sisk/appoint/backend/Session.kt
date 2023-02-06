package com.sisk.appoint.backend

import java.time.Duration
import java.time.LocalTime
import java.time.temporal.ChronoUnit

data class Session(val start: LocalTime, val end: LocalTime, val interval: Duration, val name: String){
    val sessionDuration = start.until(end, ChronoUnit.MINUTES)
}