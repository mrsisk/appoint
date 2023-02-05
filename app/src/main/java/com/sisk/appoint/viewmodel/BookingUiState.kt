package com.sisk.appoint.viewmodel

import com.sisk.appoint.model.AppointDateTime
import com.sisk.appoint.model.Location

data class BookingUiState(
    val location: Location = Location(name = "Mbabane, Government Hospital"),
    val appointDateTime: AppointDateTime = AppointDateTime(),
    val dateTime: List<AppointDateTime> = emptyList(),
    val locationSuggestions: List<Location> = emptyList()
)