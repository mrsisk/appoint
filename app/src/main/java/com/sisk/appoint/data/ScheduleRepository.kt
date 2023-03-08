package com.sisk.appoint.data

import android.util.Log
import com.sisk.appoint.model.Booking
import com.sisk.appoint.model.BookingRequest
import com.sisk.appoint.model.WorkDay
import com.sisk.appoint.network.ScheduleApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleRepository @Inject constructor(private val scheduleApi: ScheduleApi) {

    suspend fun getSchedule(): Flow<List<WorkDay>> = flow {
        val response = scheduleApi.dateTime()
        if (response.isSuccessful){
            emit(response.body() ?: emptyList())
        }else{
            throw Exception("Failed to get schedule")
        }
    }

    suspend fun book(request: BookingRequest): Flow<Booking> = flow {
        val response = scheduleApi.book(request)

        if (response.isSuccessful){
            emit(response.body() ?: throw Exception("Empty booking body"))
        }else{
            throw Exception("Booking failed ${response.code()}")
        }
    }
}