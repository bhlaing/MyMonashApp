package com.billy.mymonashapp.data.pofile

import com.billy.mymonashapp.domain.profile.StudentProfile
import kotlinx.coroutines.flow.Flow

interface StudentService {
    fun observeStudentProfileByWeek(): Flow<StudentProfile?>
}