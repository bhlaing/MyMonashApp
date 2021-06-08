package com.billy.mymonashapp.domain

import com.billy.mymonashapp.BaseTest
import com.billy.mymonashapp.data.lecture.buildLecture
import com.billy.mymonashapp.data.lecture.buildStudentLecture
import com.billy.mymonashapp.domain.lecture.mapToStudentProfile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StudentLectureMapperTest : BaseTest() {
    private val studentLectureDO = buildStudentLecture(
        listOf(
            buildLecture(
                fromTime = "8:00 AM",
                toTime = "10:00 AM",
                name = "FIT1031 Lecture 01",
                lecturer = "Arun Kongaurthu",
                campusInfoString = "S4, 13 College Walk, Clayton"
            ),
            buildLecture(
                fromTime = "9:00 AM",
                toTime = "11:00 AM",
                name = "FIT1031 Lecture 02",
                lecturer = "Ray Kurzweil",
                campusInfoString = "S4, 13 Tafe Walk, Clayton"
            )
        )
    )

    @Test
    fun `given a student lecture profile data with lectures, when mapping, then maps lectures correctly`() {
        val profile = mapToStudentProfile(studentLectureDO)

        assertTrue(profile.lectures.size == 2)
        with(profile.lectures.first()) {
            assertEquals("8:00 AM", fromTime)
            assertEquals("10:00 AM", toTime)
            assertEquals("FIT1031 Lecture 01", name)
            assertEquals("Arun Kongaurthu", lecturer)
            assertEquals("S4, 13 College Walk, Clayton", campusInfoString)
        }

        with(profile.lectures.component2()) {
            assertEquals("9:00 AM", fromTime)
            assertEquals("11:00 AM", toTime)
            assertEquals("FIT1031 Lecture 02", name)
            assertEquals("Ray Kurzweil", lecturer)
            assertEquals("S4, 13 Tafe Walk, Clayton", campusInfoString)
        }
    }
}