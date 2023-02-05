package com.sisk.appoint.viewmodel

import com.sisk.appoint.model.Category
import com.sisk.appoint.model.Info


data class AppointAppState(
    val categories: List<Category> = emptyList(),
    val info: Info = Info()
)