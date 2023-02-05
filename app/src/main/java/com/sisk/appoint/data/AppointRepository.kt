package com.sisk.appoint.data

import com.sisk.appoint.model.Location
import kotlinx.coroutines.flow.Flow

interface AppointRepository {
    val suggestions:Flow<List<Location>>

    suspend fun findLocation(query: String): List<Location>
}

