package com.sisk.appoint.data

import com.sisk.appoint.model.AppointDate
import com.sisk.appoint.model.Location
import com.sisk.appoint.model.Period
import kotlinx.coroutines.flow.Flow

interface AppointRepository {
    val suggestions:Flow<List<Location>>

    suspend fun findLocation(query: String): List<Location>

    val workingDays: Flow<List<AppointDate>>

    val workingPeriods: Flow<Map<String, List<Period>>>
}

