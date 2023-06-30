package com.sisk.appoint.network

import com.sisk.appoint.model.AppointUser
import com.sisk.appoint.model.CreateProfileRequest
import com.sisk.appoint.model.Profile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("/auth/userinfo")
    suspend fun userinfo(): Response<AppointUser>

    @POST("profile")
    suspend fun profile(@Body createProfileRequest: CreateProfileRequest): Response<Profile>
}