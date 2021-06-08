package com.billy.mymonashapp.domain.lecture._di

import com.billy.mymonashapp.data.lecture.StudentLectureService
import com.billy.mymonashapp.data.lecture.StudentLectureServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LectureModule {
    @Provides
    @Singleton
    fun provideStudentLectureService(studentService: StudentLectureServiceImpl): StudentLectureService = studentService
}