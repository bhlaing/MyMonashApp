package com.billy.mymonashapp.domain.shuttlebus._di

import com.billy.mymonashapp.data.shuttlebus.ShuttleBusService
import com.billy.mymonashapp.data.shuttlebus.ShuttleBusServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShuttleBusModule {
    @Provides
    @Singleton
    fun provideShuttleBustService(studentService: ShuttleBusServiceImpl): ShuttleBusService = studentService
}