package com.billy.mymonashapp.data.pofile

import com.billy.mymonashapp.domain.builders.buildStudentProfile
import com.billy.mymonashapp.domain.profile.StudentProfile
import com.billy.mymonashapp.domain.profile.mapToStudentProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudentServiceImpl @Inject constructor(): StudentService {
    /**
     * A hardcode flow with delay 1 second to simulate asynchronous flow.
     * Please see CarParkService or ShuttleBusService for FirebaseFirestore implementation
     */
    override fun observeStudentProfileByWeek(): Flow<StudentProfile?> =
        try {
            flow {
                delay(1000)
                emit(mapToStudentProfile(buildStudentProfile()))
            }
        } catch (ex: Exception) {
            flow { emit(null) }
        }

}