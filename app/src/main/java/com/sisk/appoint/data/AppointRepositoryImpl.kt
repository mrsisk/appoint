package com.sisk.appoint.data

import android.util.Log
import com.sisk.appoint.backend.*
import com.sisk.appoint.model.AppointDate
import com.sisk.appoint.model.Location
import com.sisk.appoint.model.Period
import com.sisk.appoint.network.AppointMainApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.time.ZoneId
import javax.inject.Inject

class AppointRepositoryImpl @Inject constructor(private val appointMainApi: AppointMainApi): AppointRepository {
    override val suggestions: Flow<List<Location>> = flow { emit(locations().take(3)) }

    override val workingDays: Flow<List<AppointDate>>
        get() = flow {
            val days = AppointDateFactory(ZoneId.of("Africa/Johannesburg")).workingDays(7)
            emit(days)
        }

    override val workingPeriods: Flow<Map<String, List<Period>>>
        get() = flow {
            val period = AppointTimeFactory().getWorkingPeriods()
            emit(period)
        }

    override suspend fun test() {
        try {
           val res =  appointMainApi.getData()
            if (res.isSuccessful){
                Log.d("mama", "result ar ${res.body()}")
            }else{
                Log.d("mama", "result error ${res.errorBody()} ${res.code()}")
            }

        }catch (ex: Exception){
            Log.d("mama", ex.message ?: "Exception in api call")
        }

    }

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