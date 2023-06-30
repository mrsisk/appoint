package com.sisk.appoint.network


import com.sisk.appoint.data.TokenRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AppointInterceptor @Inject constructor(private val tokenRepository: TokenRepository): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenRepository.token.first()
        }

        val request = chain.request().newBuilder()


        if (token != null){
            request.addHeader("Authorization", "Bearer $token")

        }


        return chain.proceed(request.build())
    }

}