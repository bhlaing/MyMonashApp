package com.billy.mymonashapp.data.pofile

import com.billy.mymonashapp.domain.StudentProfile
import com.billy.mymonashapp.domain.mapToStudentProfile
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