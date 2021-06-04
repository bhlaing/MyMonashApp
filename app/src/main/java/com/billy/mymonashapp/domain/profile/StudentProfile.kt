package com.billy.mymonashapp.domain.profile

class StudentProfile(
    val name: String,
    val lectures: List<Lecture>
) {
    class Lecture(
        val fromTime: String,
        val toTime: String,
        val name: String,
        val lecturer: String,
        val campusInfoString: String
    )
}