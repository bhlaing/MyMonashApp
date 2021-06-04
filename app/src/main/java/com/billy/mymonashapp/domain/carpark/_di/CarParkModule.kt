package com.billy.mymonashapp.domain.carpark._di

import com.billy.mymonashapp.data.carpark.CarParkService
import com.billy.mymonashapp.data.carpark.CarParkServiceImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CarParkModule {
    @Provides
    @Singleton
    fun provideCarParkService(carParkService: CarParkServiceImpl): CarParkService = carParkService
}