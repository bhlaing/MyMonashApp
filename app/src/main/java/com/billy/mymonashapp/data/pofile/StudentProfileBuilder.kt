package com.billy.mymonashapp.data.pofile

fun buildStudentProfile(
    name: String = "Kier",
    lectures: List<StudentProfileDO.LectureDO> = listOf(buildLecture()),
    carParks: List<StudentProfileDO.CarParkDO> = listOf(buildCarPark()),
    shuttleBuses: List<StudentProfileDO.ShuttleBusDO> = listOf(buildShuttleBus())
) = StudentProfileDO(name, lectures, carParks, shuttleBuses)

fun buildLecture(
    fromTime: String = "8:00 AM",
    toTime: String = "10:00 AM",
    name: String = "FIT1031 Lecture 01",
    lecturer: String = "Arun Kongaurthu",
    campusInfoString: String = "S4, 13 College Walk, Clayton"
) = StudentProfileDO.LectureDO(fromTime, toTime, name, lecturer, campusInfoString)


fun buildCarPark(name: String = "Clayton live feed",
                 spaces: String = "645") = StudentProfileDO.CarParkDO(name, spaces)

fun buildShuttleBus(
    from: String = "Clayton",
    to: String = "Caufield",
    duration: String = "4 mins"
) = StudentProfileDO.ShuttleBusDO(from, to, duration)
