package com.sisk.appoint.ui.viewmodel

import com.sisk.appoint.model.AppointUser
import com.sisk.appoint.model.Category
import com.sisk.appoint.model.Info


data class AppointAppState(
    val categories: List<Category> = emptyList(),
    val info: Info = Info(),
    val user: AppointUser? = null,
    val isLoading: Boolean = true,
)