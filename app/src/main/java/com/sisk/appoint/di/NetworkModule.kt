package com.sisk.appoint.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import com.sisk.appoint.data.AuthRepository
import com.sisk.appoint.data.TokenRepository
import com.sisk.appoint.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Qualifier
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
const val API_BASE_URL = "http://192.168.1.3:8888/"
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @MainInterceptor
    @Provides
    fun provideMainHttpClient(
        appointInterceptor: AppointInterceptor,
        appointAuthenticator: AppointAuthenticator,
        cookieJar: AppointCookieJar
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(appointInterceptor)
        .authenticator(appointAuthenticator)
        .followRedirects(false)
        .followSslRedirects(false)
        .cookieJar(cookieJar)
        .build()

    @AuthInterceptor
    @Provides
    @Singleton
    fun provideAuthHttpClient(cookieJar: AppointCookieJar): OkHttpClient = OkHttpClient
        .Builder()
        .cookieJar(cookieJar)
        .build()

    @Provides
    @Singleton
    fun provideCookieJar(tokenRepository: TokenRepository): AppointCookieJar = AppointCookieJar(tokenRepository)

    private val localTimeDeserializer = JsonDeserializer { json, _, _ ->
        ZonedDateTime.parse(json?.asJsonPrimitive?.asString)
    }


    private val localDateDeserializer = JsonDeserializer { json, _, _ ->
        LocalDate.parse(json?.asJsonPrimitive?.asString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    private val gson  = GsonBuilder()
        .registerTypeAdapter(ZonedDateTime::class.java, localTimeDeserializer)
        .registerTypeAdapter(LocalDate::class.java, localDateDeserializer)
        .create()
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Provides
    @Singleton
    fun provideAppointApi(retrofit: Retrofit.Builder, @MainInterceptor client: OkHttpClient): AppointMainApi = retrofit
        .client(client)
        .build()
        .create(AppointMainApi::class.java)

    @Provides
    @Singleton
    fun provideScheduleApi(retrofit: Builder,@MainInterceptor client: OkHttpClient): ScheduleApi = retrofit
        .client(client)
        .build()
        .create(ScheduleApi::class.java)
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Builder,@MainInterceptor client: OkHttpClient): UserApi = retrofit
        .client(client)
        .build()
        .create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideAppointAuthApi(retrofit: Retrofit.Builder, @AuthInterceptor client: OkHttpClient): AppointAuthApi = retrofit
        .client(client)
        .build()
        .create(AppointAuthApi::class.java)
    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenRepository = TokenRepository(context)

    @Provides
    @Singleton
    fun provideAppointInterceptor(tokenRepository: TokenRepository): AppointInterceptor = AppointInterceptor(tokenRepository)

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AppointAuthApi): AuthRepository = AuthRepository(authApi)

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainInterceptor