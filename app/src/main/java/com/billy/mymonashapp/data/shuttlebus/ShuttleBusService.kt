package com.billy.mymonashapp.data.shuttlebus

import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import kotlinx.coroutines.flow.Flow

interface ShuttleBusService {
    fun observeShuttleBuses(): Flow<ShuttleBusSchedule?>
}