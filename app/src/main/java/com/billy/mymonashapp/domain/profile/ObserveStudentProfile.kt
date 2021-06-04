package com.billy.mymonashapp.domain.profile

import com.billy.mymonashapp.data.pofile.StudentService
import com.billy.mymonashapp.domain.ResultInteractor
import com.billy.mymonashapp.domain.StudentProfile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveStudentProfile @Inject constructor(private val studentService: StudentService) :
    ResultInteractor<Int, Flow<StudentProfile?>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun doWork(params: Int): Flow<StudentProfile?> = studentService.observeStudentProfileByWeek()
}