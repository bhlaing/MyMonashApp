package com.billy.mymonashapp.domain.carpark

class AvailableCarParks (val parkings: List<CarPark>) {
    class CarPark(
        val carparkName: String,
        val availableSpaces: String
    )
}