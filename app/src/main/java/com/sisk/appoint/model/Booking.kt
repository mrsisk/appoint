package com.sisk.appoint.model

import java.time.ZonedDateTime

data class Booking(val id: Long, val start: ZonedDateTime, val endTime: ZonedDateTime, val description: String)
