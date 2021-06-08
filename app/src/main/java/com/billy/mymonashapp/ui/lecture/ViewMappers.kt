package com.billy.mymonashapp.ui.lecture

import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.lecture.StudentLecture
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.ui.lecture.model.CarParkItem
import com.billy.mymonashapp.ui.lecture.model.LectureItem
import com.billy.mymonashapp.ui.lecture.model.ShuttleBusItem

fun mapToLecture(lecture: StudentLecture.Lecture) =
    LectureItem(lecture.fromTime,
        lecture.toTime,
        lecture.name,
        lecture.lecturer,
        lecture.campusInfoString)

fun mapToShuttleBus(bus: ShuttleBusSchedule.ShuttleBus) =
    ShuttleBusItem(bus.from, bus.to, bus.duration)

fun mapToCarParkInfo(carpark: AvailableCarParks.CarPark) =
    CarParkItem(carpark.carparkName, carpark.availableSpaces)