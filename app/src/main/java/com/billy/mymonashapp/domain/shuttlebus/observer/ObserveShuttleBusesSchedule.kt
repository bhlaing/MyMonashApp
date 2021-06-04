package com.billy.mymonashapp.domain.shuttlebus.observer

import com.billy.mymonashapp.data.shuttlebus.ShuttleBusService
import com.billy.mymonashapp.domain.ResultInteractor
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveShuttleBusesSchedule @Inject constructor(private val shuttleBusService: ShuttleBusService) :
    ResultInteractor<Unit, Flow<ShuttleBusSchedule?>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun doWork(params: Unit): Flow<ShuttleBusSchedule?> = shuttleBusService.observeShuttleBuses()
}