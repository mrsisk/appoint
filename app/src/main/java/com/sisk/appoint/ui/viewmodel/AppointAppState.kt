package com.sisk.appoint.ui.viewmodel

import com.sisk.appoint.model.Category
import com.sisk.appoint.model.Info


data class AppointAppState(
    val categories: List<Category> = emptyList(),
    val info: Info = Info()
)