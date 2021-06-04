package com.billy.mymonashapp.data.pofile

import com.billy.mymonashapp.domain.StudentProfile
import kotlinx.coroutines.flow.Flow

interface StudentService {
    fun observeStudentProfileByWeek(): Flow<StudentProfile?>
}