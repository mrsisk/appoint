package com.sisk.appoint.network

import android.util.Log
import com.sisk.appoint.data.TokenRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AppointAuthenticator @Inject constructor(private val tokenRepository: TokenRepository): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {


        if (!containsAccessToken(response)) return null


        val refreshToken = runBlocking {
            tokenRepository.refreshToken.first()
        } ?: return null

        val newAccessToken = runBlocking {
            tokenRepository.refresh(refreshToken)
        } ?: return null

        return response.request
            .newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()

    }

    private fun containsAccessToken(response: Response): Boolean{
        val header = response.request.header("Authorization")
        return header != null && header.startsWith("Bearer")
    }
}