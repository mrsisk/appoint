package com.sisk.appoint.data.di

import com.sisk.appoint.data.AppointRepository
import com.sisk.appoint.data.AppointRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppointModule {
    @Binds
    fun bindAppointRepository(appointRepositoryImpl: AppointRepositoryImpl): AppointRepository
}