package com.sisk.appoint.model

import java.time.ZonedDateTime

data class BookingRequest(val start: String, val end: String, val description: String)
