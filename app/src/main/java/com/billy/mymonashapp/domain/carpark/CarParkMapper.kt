package com.billy.mymonashapp.domain.carpark

import com.billy.mymonashapp.data.carpark.AvailableCarParksDO

fun mapToAvailableCarParks(carParksDO: AvailableCarParksDO) =
    AvailableCarParks(carParksDO.parkings.map {
        // !! because we have default values for data objects
        AvailableCarParks.CarPark(
            it.carparkName!!,
            it.availableSpaces!!
        )
    })