package com.billy.mymonashapp.domain.profile

import com.billy.mymonashapp.data.pofile.StudentProfileDO

fun mapToStudentProfile(profileDO: StudentProfileDO) =
    StudentProfile(profileDO.lectures.map {
            StudentProfile.Lecture(
                it.fromTime,
                it.toTime,
                it.name,
                it.lecturer,
                it.campusInfoString
            )
        }
    )