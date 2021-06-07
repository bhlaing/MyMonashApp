package com.billy.mymonashapp.data.shuttlebus

import com.billy.mymonashapp.application.shared.observeObject
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.mapToShuttleBusSchedule
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShuttleBusServiceImpl @Inject constructor(): ShuttleBusService{
    private val cloudFirestoreDb = FirebaseFirestore.getInstance()
    private companion object {
        const val BUS_SCHEDULES_COLLECTION = "bus_schedules"
    }

    @ExperimentalCoroutinesApi
    override fun observeShuttleBuses(): Flow<ShuttleBusSchedule?> {
        val busSchedule = cloudFirestoreDb.collection(BUS_SCHEDULES_COLLECTION)
        return busSchedule.observeObject {
            val buses = it.toObjects(ShuttleBusScheduleDO.ShuttleBusDO::class.java)
            mapToShuttleBusSchedule(ShuttleBusScheduleDO(buses))
        }
    }
}
