package com.sisk.appoint.network

import com.google.gson.GsonBuilder
import com.sisk.appoint.data.TokenRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import javax.inject.Inject

class AppointCookieJar @Inject constructor(private val tokenRepository: TokenRepository) : CookieJar {

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val jsonCookies = runBlocking {
            tokenRepository.refreshToken.first()
        }

       if (jsonCookies != null){
           val cookies = GsonBuilder().create().fromJson(jsonCookies, Array<Cookie>::class.java)
           return cookies.toList()
       }

       return emptyList()


    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val cookiesArray = cookies.toTypedArray()
        val jsonCookies = GsonBuilder().setPrettyPrinting().create().toJson(cookiesArray)
        runBlocking {
            tokenRepository.saveRefreshToken(jsonCookies)
        }
    }
}