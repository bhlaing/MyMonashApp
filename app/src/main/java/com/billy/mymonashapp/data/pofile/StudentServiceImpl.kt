package com.billy.mymonashapp.data.pofile

import com.billy.mymonashapp.domain.profile.StudentProfile
import com.billy.mymonashapp.domain.profile.mapToStudentProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudentServiceImpl @Inject constructor(): StudentService {
    override fun observeStudentProfileByWeek(): Flow<StudentProfile?> =
        try {
            flow {
                delay(2000)
                emit(mapToStudentProfile(buildStudentProfile()))
            }
        } catch (ex: Exception) {
            flow { emit(null) }
        }

}