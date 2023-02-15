package com.sisk.appoint.di

import com.sisk.appoint.data.AppointRepository
import com.sisk.appoint.data.AppointRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppointModule {
    @Binds
    abstract fun bindAppointRepository(appointRepositoryImpl: AppointRepositoryImpl): AppointRepository
}