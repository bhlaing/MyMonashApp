package com.billy.mymonashapp.data.lecture

class StudentLectureDO(
    val lectures: List<LectureDO>
) {
    class LectureDO(
        val fromTime: String,
        val toTime: String,
        val name: String,
        val lecturer: String,
        val campusInfoString: String
    )
}