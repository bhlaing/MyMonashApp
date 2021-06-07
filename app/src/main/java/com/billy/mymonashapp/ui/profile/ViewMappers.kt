package com.billy.mymonashapp.ui.profile

import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.profile.StudentProfile
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.ui.profile.model.CarPark
import com.billy.mymonashapp.ui.profile.model.Lecture
import com.billy.mymonashapp.ui.profile.model.ShuttleBus

fun mapToLecture(lecture: StudentProfile.Lecture) =
    Lecture(lecture.fromTime,
        lecture.toTime,
        lecture.name,
        lecture.lecturer,
        lecture.campusInfoString)

fun mapToShuttleBus(bus: ShuttleBusSchedule.ShuttleBus) =
    ShuttleBus(bus.from, bus.to, bus.duration)

fun mapToCarParkInfo(carpark: AvailableCarParks.CarPark) =
    CarPark(carpark.carparkName, carpark.availableSpaces)