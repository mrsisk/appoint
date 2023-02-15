package com.sisk.appoint.data

import com.google.gson.Gson
import com.sisk.appoint.model.AuthRequest
import com.sisk.appoint.model.GenericError
import com.sisk.appoint.model.LogInResponse
import com.sisk.appoint.model.RegistrationError
import com.sisk.appoint.network.AppointAuthApi
import com.sisk.appoint.network.AppointResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AppointAuthApi) {
    //REGISTER STATE LOADING ERROR SUCCESS

    suspend fun register(authRequest: AuthRequest): Flow<AppointResponse<String, RegistrationError>>{
        return flow {
            val response = authApi.register(authRequest)

            if (response.isSuccessful){
                emit(AppointResponse.Success(response.body()?.message ?: "NO MESSAGE"))
            }else{
                val error = response.errorBody()?.charStream()
                if (error != null){
                    val regError = Gson().fromJson(error, RegistrationError::class.java)
                    emit(AppointResponse.Error(regError))
                }else{
                  //  emit(AppointResponse.Error("Registration failed"))
                }

            }
        }
    }

    suspend fun authenticate(authRequest: AuthRequest): Flow<AppointResponse<LogInResponse, GenericError>> = flow {
        try {
            val response = authApi.authenticate(authRequest)
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    emit(AppointResponse.Success(body))
                }else{
                    emit(AppointResponse.Error(GenericError("Unknown error")))
                }
            } else{
                val error = response.errorBody()?.charStream()
                if (error != null){
                    val genericError = Gson().fromJson(error, GenericError::class.java)
                    emit(AppointResponse.Error(genericError))
                }
            }
        }catch (e: Exception){
            //handle error
            emit(AppointResponse.Error(GenericError("Unknown error occurred")))
        }

    }
}