package com.billy.mymonashapp.domain.carpark.observer

import com.billy.mymonashapp.data.carpark.CarParkService
import com.billy.mymonashapp.domain.ResultInteractor
import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAvailableCarParks @Inject constructor(private val carParkService: CarParkService) :
    ResultInteractor<Unit, Flow<AvailableCarParks?>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun doWork(params: Unit): Flow<AvailableCarParks?> = carParkService.observeCarparks()
}