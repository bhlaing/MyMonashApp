package com.billy.mymonashapp.domain.builders

import com.billy.mymonashapp.data.pofile.StudentProfileDO

fun buildStudentProfile(
    lectures: List<StudentProfileDO.LectureDO> = listOf(buildLecture(), buildLecture())
) = StudentProfileDO(lectures)

fun buildLecture(
    fromTime: String = "8:00 AM",
    toTime: String = "10:00 AM",
    name: String = "FIT1031 Lecture 01",
    lecturer: String = "Arun Kongaurthu",
    campusInfoString: String = "S4, 13 College Walk, Clayton"
) = StudentProfileDO.LectureDO(fromTime, toTime, name, lecturer, campusInfoString)

