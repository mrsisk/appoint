package com.sisk.appoint.network

import retrofit2.Response
import retrofit2.http.GET

interface AppointMainApi {
    @GET("hello")
    suspend fun getData(): Response<TestUser>
}