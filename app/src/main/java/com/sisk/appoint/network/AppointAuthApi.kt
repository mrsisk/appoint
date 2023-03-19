package com.sisk.appoint.network

import com.sisk.appoint.model.AuthRequest
import com.sisk.appoint.model.LogInResponse
import com.sisk.appoint.model.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppointAuthApi {
    @POST("auth/register")
    suspend fun register(@Body authRequest: AuthRequest): Response<RegistrationResponse>

    @POST("auth/authenticate")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<LogInResponse>

    @POST("auth/refresh")
    suspend fun refresh(): Response<LogInResponse>

    @GET("auth/test")
    suspend fun test(): Response<String>
}