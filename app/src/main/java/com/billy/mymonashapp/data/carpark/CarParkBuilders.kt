package com.billy.mymonashapp.data.carpark

fun buildAvailableCarParks(carParks: List<AvailableCarParksDO.CarParkDO> = listOf(buildCarPark())) =
    AvailableCarParksDO(carParks)

fun buildCarPark(name: String = "Clayton live feed",
                 spaces: String = "645") = AvailableCarParksDO.CarParkDO(name, spaces)