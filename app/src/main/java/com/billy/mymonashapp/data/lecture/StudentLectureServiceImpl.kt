package com.billy.mymonashapp.data.lecture

import com.billy.mymonashapp.domain.lecture.StudentLecture
import com.billy.mymonashapp.domain.lecture.mapToStudentProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudentLectureServiceImpl @Inject constructor(): StudentLectureService {
    /**
     * A hardcoded flow with delay 1 second to simulate asynchronous flow.
     * Please see CarParkService or ShuttleBusService for FirebaseFirestore implementation
     */
    override fun observeStudentProfileByWeek(): Flow<StudentLecture?> =
        try {
            flow {
                delay(1000)
                emit(mapToStudentProfile(buildStudentLecture()))
            }
        } catch (ex: Exception) {
            flow { emit(null) }
        }
}