package com.sisk.appoint.viewmodel

import com.sisk.appoint.model.AppointDate
import com.sisk.appoint.model.Location
import com.sisk.appoint.model.Period

data class BookingUiState(
    val location: Location = Location(name = "Mbabane, Government Hospital"),
    val appointDate: AppointDate = AppointDate(),
    val days: List<AppointDate> = emptyList(),
    val locationSuggestions: List<Location> = emptyList(),
    val morningPeriods: List<Period> = emptyList(),
    val afternoonPeriods: List<Period> = emptyList(),
    val appointPeriod: Period = Period()
)