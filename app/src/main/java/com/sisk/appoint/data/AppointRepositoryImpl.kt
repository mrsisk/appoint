package com.sisk.appoint.data

import com.sisk.appoint.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppointRepositoryImpl @Inject constructor(): AppointRepository {
    override val suggestions: Flow<List<Location>> = flow { emit(locations().take(3)) }

    override suspend fun findLocation(query: String): List<Location> {
        return withContext(Dispatchers.IO){
            locations().filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}

fun locations() = listOf(
    Location(name = "Mbabane, Government hospital", latitude = 0.0, longitude = 0.0),
    Location(name = "Manzini, hospital", latitude = 0.0, longitude = 0.0),
    Location(name = "Pigs Peak, hospital", latitude = 0.0, longitude = 0.0),
    Location(name = "Nhlangano, hospital", latitude = 0.0, longitude = 0.0),
    Location(name = "Siteki, hospital", latitude = 0.0, longitude = 0.0)
)