package com.billy.mymonashapp.data.lecture

fun buildStudentLecture(
    lectures: List<StudentLectureDO.LectureDO> = listOf(buildLecture(), buildLecture())
) = StudentLectureDO(lectures)

fun buildLecture(
    fromTime: String = "8:00 AM",
    toTime: String = "10:00 AM",
    name: String = "FIT1031 Lecture 01",
    lecturer: String = "Arun Kongaurthu",
    campusInfoString: String = "S4, 13 College Walk, Clayton"
) = StudentLectureDO.LectureDO(fromTime, toTime, name, lecturer, campusInfoString)

