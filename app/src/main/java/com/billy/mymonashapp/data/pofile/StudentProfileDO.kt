package com.billy.mymonashapp.data.pofile

class StudentProfileDO(
    val name: String,
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