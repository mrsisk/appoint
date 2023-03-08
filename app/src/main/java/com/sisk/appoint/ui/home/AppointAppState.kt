package com.sisk.appoint.ui.home

import com.sisk.appoint.model.AppointUser
import com.sisk.appoint.model.CategoryUi
import com.sisk.appoint.model.Info


data class AppointAppState(
    val categories: List<CategoryUi> = emptyList(),
    val info: Info = Info(),
    val user: AppointUser? = null,
    val isLoading: Boolean = true,
)