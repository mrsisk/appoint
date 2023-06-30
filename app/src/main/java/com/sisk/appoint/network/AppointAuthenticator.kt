package com.sisk.appoint.network

import com.sisk.appoint.data.TokenRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AppointAuthenticator @Inject constructor(private val tokenRepository: TokenRepository, private val appointAuthApi: AppointAuthApi): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (!containsAccessToken(response)) return null

        runBlocking {
            tokenRepository.refreshToken.first()
        } ?: return null

        val newAccessToken = runBlocking {

            val res = appointAuthApi.refresh()

            if (res.isSuccessful){
                val token = res.body()
                if (token == null){
                    return@runBlocking null
                }else{
                    tokenRepository.saveToken(token.access_token)
                    return@runBlocking token.access_token
                }
            }else{
                return@runBlocking null
            }
        } ?: return null

        return  response.request
            .newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()



    }

    private fun containsAccessToken(response: Response): Boolean{
        val header = response.request.header("Authorization")
        return header != null && header.startsWith("Bearer")
    }
}