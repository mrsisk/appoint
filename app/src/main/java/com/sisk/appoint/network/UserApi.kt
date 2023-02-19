package com.sisk.appoint.network

import com.sisk.appoint.model.AppointUser
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("userinfo")
    suspend fun userinfo(): Response<AppointUser>
}