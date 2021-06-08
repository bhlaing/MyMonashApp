package com.billy.mymonashapp.data.lecture

import com.billy.mymonashapp.domain.lecture.StudentLecture
import kotlinx.coroutines.flow.Flow

interface StudentLectureService {
    fun observeStudentProfileByWeek(): Flow<StudentLecture?>
}