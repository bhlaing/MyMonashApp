package com.billy.mymonashapp.data.pofile

class StudentProfileDO(
    val name: String,
    val lectures: List<LectureDO>,
    val carParks: List<CarParkDO>,
    val shuttleBuses: List<ShuttleBusDO>
) {
    class LectureDO(
        val fromTime: String,
        val toTime: String,
        val name: String,
        val lecturer: String,
        val campusInfoString: String
    )

    class CarParkDO(
        val carparkName: String,
        val availableSpaces: String
    )

    class ShuttleBusDO(
        val from: String,
        val to: String,
        val duration: String
    )
}