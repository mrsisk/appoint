package com.sisk.appoint.network

import com.sisk.appoint.model.Booking
import com.sisk.appoint.model.BookingRequest
import com.sisk.appoint.model.WorkDay
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ScheduleApi {
    @GET("api/v1/schedule/days")
    suspend fun dateTime(): Response<List<WorkDay>>

    @POST("booking")
    suspend fun book(@Body request: BookingRequest): Response<Booking>
}