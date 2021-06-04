package com.billy.mymonashapp.domain

import com.billy.mymonashapp.data.pofile.StudentProfileDO

fun mapToStudentProfile(profileDO: StudentProfileDO) =
    StudentProfile(profileDO.name,
        profileDO.lectures.map {
            StudentProfile.Lecture(
                it.fromTime,
                it.toTime,
                it.name,
                it.lecturer,
                it.campusInfoString
            )
        },
        profileDO.carParks.map {
            StudentProfile.CarPark(it.carparkName, it.availableSpaces)
        },
        profileDO.shuttleBuses.map {
            StudentProfile.ShuttleBus(it.from, it.to, it.duration)
        }
    )