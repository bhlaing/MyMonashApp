package com.billy.mymonashapp.data.carpark

import com.billy.mymonashapp.application.shared.observeObject
import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.carpark.mapToAvailableCarParks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarParkServiceImpl @Inject constructor(): CarParkService {
    private val cloudFirestoreDb = FirebaseFirestore.getInstance()
    private companion object {
        const val CARPARK_COLLECTION = "parking"
    }

    @ExperimentalCoroutinesApi
    override fun observeCarparks(): Flow<AvailableCarParks?> {
        val carparks = cloudFirestoreDb.collection(CARPARK_COLLECTION)
        return carparks.observeObject {
            val buses = it.toObjects(AvailableCarParksDO.CarParkDO::class.java)
            mapToAvailableCarParks(AvailableCarParksDO(buses))
        }
    }

}