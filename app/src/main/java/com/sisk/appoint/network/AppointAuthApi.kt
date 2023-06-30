package com.sisk.appoint.network

import com.sisk.appoint.model.AuthRequest
import com.sisk.appoint.model.LogInResponse
import com.sisk.appoint.model.RegistrationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AppointAuthApi {
    @POST("auth/user/register")
    suspend fun register(
        @Body authRequest: RegistrationRequest
    ): Response<Unit>

    @POST("auth/login")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<LogInResponse>

    @POST("auth/refresh")
    suspend fun refresh(): Response<LogInResponse>

    @GET("auth/test")
    suspend fun test(): Response<String>
}