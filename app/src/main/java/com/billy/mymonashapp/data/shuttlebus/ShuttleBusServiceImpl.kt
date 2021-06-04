package com.billy.mymonashapp.data.shuttlebus

import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.mapToShuttleBusSchedule
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShuttleBusServiceImpl @Inject constructor(): ShuttleBusService{
    override fun observeShuttleBuses(): Flow<ShuttleBusSchedule?> =
        try {
            flow {
                delay(2000)
                emit(mapToShuttleBusSchedule(buildShuttleBusSchedule()))
            }
        } catch (ex: Exception) {
            flow { emit(null) }
        }
}