package com.billy.mymonashapp.domain

class StudentProfile(
    val name: String,
    val lectures: List<Lecture>,
    val carParks: List<CarPark>,
    val shuttleBuses: List<ShuttleBus>
) {
    class Lecture(
        val fromTime: String,
        val toTime: String,
        val name: String,
        val lecturer: String,
        val campusInfoString: String
    )

    class CarPark(
        val carparkName: String,
        val availableSpaces: String
    )

    class ShuttleBus(
        val from: String,
        val to: String,
        val duration: String
    )
}