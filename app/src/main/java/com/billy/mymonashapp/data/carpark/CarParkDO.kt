package com.billy.mymonashapp.data.carpark

class AvailableCarParksDO(val parkings: List<CarParkDO>) {
    class CarParkDO(
        val carparkName: String? = "",
        val availableSpaces: String? = ""
    )
}