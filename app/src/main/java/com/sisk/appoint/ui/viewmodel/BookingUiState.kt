package com.sisk.appoint.ui.viewmodel

import com.sisk.appoint.model.*

data class BookingUiState(
    val location: Location = Location(name = "Mbabane, Government Hospital"),
    val locationSuggestions: List<Location> = emptyList(),
    val schedules: List<WorkDayUi> = emptyList(),
    val schedule: WorkDayUi = WorkDayUi(),
    val workPeriod: WorkPeriod? = WorkPeriod(),
    val additionalInfo: String = "",
    val category: Category = Category.GENERIC
)