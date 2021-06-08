package com.billy.mymonashapp.domain.lecture

import com.billy.mymonashapp.data.lecture.StudentLectureDO

fun mapToStudentProfile(lectureDO: StudentLectureDO) =
    StudentLecture(lectureDO.lectures.map {
            StudentLecture.Lecture(
                it.fromTime,
                it.toTime,
                it.name,
                it.lecturer,
                it.campusInfoString
            )
        }
    )