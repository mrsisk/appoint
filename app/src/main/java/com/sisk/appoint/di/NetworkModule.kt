package com.sisk.appoint.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sisk.appoint.data.AuthRepository
import com.sisk.appoint.data.TokenRepository
import com.sisk.appoint.network.AppointMainApi
import com.sisk.appoint.network.AppointAuthApi
import com.sisk.appoint.network.AppointAuthenticator
import com.sisk.appoint.network.AppointInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @MainInterceptor
    @Provides
    fun provideMainHttpClient(
        appointInterceptor: AppointInterceptor,
        appointAuthenticator: AppointAuthenticator
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(appointInterceptor)
        .authenticator(appointAuthenticator)
        .followRedirects(false)
        .followSslRedirects(false)
        .build()

    @AuthInterceptor
    @Provides
    fun provideAuthHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder = Retrofit.Builder()
            .baseUrl("http://192.168.1.6:8081/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideAppointApi(retrofit: Retrofit.Builder, @MainInterceptor client: OkHttpClient): AppointMainApi = retrofit
        .client(client)
        .build()
        .create(AppointMainApi::class.java)

    @Provides
    @Singleton
    fun provideAppointAuthApi(retrofit: Retrofit.Builder, @AuthInterceptor client: OkHttpClient): AppointAuthApi = retrofit
        .client(client)
        .build()
        .create(AppointAuthApi::class.java)
    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context, authApi: AppointAuthApi): TokenRepository = TokenRepository(context,authApi)

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