package com.billy.mymonashapp.domain.lecture.observer

import com.billy.mymonashapp.data.lecture.StudentLectureService
import com.billy.mymonashapp.domain.ResultInteractor
import com.billy.mymonashapp.domain.lecture.StudentLecture
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveStudentLectures @Inject constructor(private val studentLectureService: StudentLectureService) :
    ResultInteractor<Unit, Flow<StudentLecture?>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun doWork(params: Unit): Flow<StudentLecture?> = studentLectureService.observeStudentProfileByWeek()
}