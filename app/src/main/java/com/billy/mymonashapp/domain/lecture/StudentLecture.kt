package com.billy.mymonashapp.domain.lecture

class StudentLecture(
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