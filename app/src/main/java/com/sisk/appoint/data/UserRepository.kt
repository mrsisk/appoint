package com.sisk.appoint.data

import com.sisk.appoint.model.AppointUser
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
            if (response.code() == HTTP_UNAUTHORIZED) throw UnAuthorizedException("Authorization required")
            else throw Exception("Unknown error occure")
        }


    }
}