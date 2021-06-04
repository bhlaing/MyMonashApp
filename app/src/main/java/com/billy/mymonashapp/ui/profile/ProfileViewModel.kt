package com.billy.mymonashapp.ui.profile

import androidx.lifecycle.*
import com.billy.mymonashapp.domain.StudentProfile
import com.billy.mymonashapp.domain.profile.ObserveStudentProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val observeStudentProfile: ObserveStudentProfile
) : ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    val userProfile: LiveData<StudentProfile> =
        liveData {
            try {
                _loading.value = true
                observeStudentProfile(8).collect { profile ->
                    profile?.let { emit(it) }
                    _loading.value = false
                }
            } catch (ex: Exception) {
                // Exception handling here
                _loading.value = false
            }
        }
}