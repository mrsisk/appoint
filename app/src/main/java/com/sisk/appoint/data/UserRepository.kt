package com.sisk.appoint.data

import android.util.Log
import com.sisk.appoint.model.AppointUser
import com.sisk.appoint.model.CreateProfileRequest
import com.sisk.appoint.model.Profile
import com.sisk.appoint.network.UserApi
import com.sisk.appoint.utils.UnAuthorizedException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.internal.http.HTTP_UNAUTHORIZED
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {

    suspend fun userinfo(): Flow<AppointUser?> = flow {
        val response = userApi.userinfo()
        if (response.isSuccessful){
            emit(response.body())
        }else{
            Log.d("mama", "use repo ${response.code()} ${response.errorBody()}")
            if (response.code() == HTTP_UNAUTHORIZED) throw UnAuthorizedException("Authorization required")
            else throw Exception("failed to get userinfo error occurred")
        }

    }

    suspend fun createProfile(createProfileRequest: CreateProfileRequest): Flow<Profile?> = flow {
        val response = userApi.profile(createProfileRequest)
        if (response.isSuccessful){
            emit(response.body())
        }else{
            throw Exception("Profile update failure")
        }
    }
}