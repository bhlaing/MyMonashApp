package com.billy.mymonashapp.domain._di

import com.billy.mymonashapp.data.pofile.StudentService
import com.billy.mymonashapp.data.pofile.StudentServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StudentModule {
    @Provides
    @Singleton
    fun provideStudentService(studentService: StudentServiceImpl): StudentService = studentService
}