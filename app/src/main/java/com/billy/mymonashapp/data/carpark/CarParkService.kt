package com.billy.mymonashapp.data.carpark

import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import kotlinx.coroutines.flow.Flow

interface CarParkService {
    fun observeCarparks(): Flow<AvailableCarParks?>
}