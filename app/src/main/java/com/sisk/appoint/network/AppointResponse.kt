package com.sisk.appoint.network

sealed class AppointResponse<out T, out E>{
    data class Success<T>(val result: T): AppointResponse<T, Nothing>()
    data class Error<E>(val message: E): AppointResponse<Nothing, E>()
}

