package com.sisk.appoint.data

import android.util.Log
import com.google.gson.Gson
import com.sisk.appoint.model.AuthError
import com.sisk.appoint.model.AuthRequest
import com.sisk.appoint.model.GenericError
import com.sisk.appoint.model.LogInResponse
import com.sisk.appoint.model.RegistrationRequest
import com.sisk.appoint.network.AppointAuthApi
import com.sisk.appoint.network.AppointResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val TAG = "AuthRepository"
class AuthRepository @Inject constructor(private val authApi: AppointAuthApi) {
    //REGISTER STATE LOADING ERROR SUCCESS

    suspend fun register(authRequest: RegistrationRequest): Flow<AppointResponse<String, GenericError>>{
        return flow {
            val response = authApi.register(authRequest)

            if (response.isSuccessful){
                Log.d(TAG, "successfully register user")
                emit(AppointResponse.Success("Successful"))
            }else{
                val error = response.errorBody()?.charStream()
                if (error != null){
                    val regError = Gson().fromJson(error, GenericError::class.java)
                    Log.d(TAG, "error when registering $regError")
                emit(AppointResponse.Error(regError))
                }else{
                    Log.d(TAG, "error when registering with no message")
                  emit(AppointResponse.Error(GenericError(message = "Failed to register user")))
                }

            }
        }
    }

    suspend fun authenticate(authRequest: AuthRequest): Flow<AppointResponse<LogInResponse, AuthError>> = flow {
        try {
            val response = authApi.authenticate(authRequest)
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    emit(AppointResponse.Success(body))
                }else{
                    emit(AppointResponse.Error(AuthError(error = "Auth error", error_description = "Unknown error has occurred")))
                }
            } else{
                val error = response.errorBody()?.charStream()
                if (error != null){
                    val genericError = Gson().fromJson(error, AuthError::class.java)
                    emit(AppointResponse.Error(genericError))
                }
            }
        }catch (e: Exception){
            //handle error
            emit(AppointResponse.Error(AuthError(error = "Auth error", error_description = e.message ?: "Unknown error")))
        }

    }

}