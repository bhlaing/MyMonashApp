package com.billy.mymonashapp.data.carpark

import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.carpark.mapToAvailableCarParks
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarParkServiceImpl @Inject constructor(): CarParkService {
    override fun observeCarparks(): Flow<AvailableCarParks?> =
        try {
            flow {
                delay(2000)
                emit(mapToAvailableCarParks(buildAvailableCarParks()))
            }
        } catch (ex: Exception) {
            flow { emit(null) }
        }
}