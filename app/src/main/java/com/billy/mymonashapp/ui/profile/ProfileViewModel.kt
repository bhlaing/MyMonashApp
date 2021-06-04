package com.billy.mymonashapp.ui.profile

import androidx.lifecycle.*
import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.carpark.observer.ObserveAvailableCarParks
import com.billy.mymonashapp.domain.profile.StudentProfile
import com.billy.mymonashapp.domain.profile.observer.ObserveStudentProfile
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.observer.ObserveShuttleBusesSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val observeStudentProfile: ObserveStudentProfile,
    private val observeShuttleBusSchedule: ObserveShuttleBusesSchedule,
    private val observeAvailableCarParks: ObserveAvailableCarParks
) : ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    val userProfile: LiveData<StudentProfile> =
        liveData {
            observeStudentProfile(8).collect { profile ->
                profile?.let { emitSafely(it) }
            }
        }

    val shuttleBusSchedule: LiveData<ShuttleBusSchedule> =
        liveData {
            observeShuttleBusSchedule(Unit).collect { busSchedule ->
                busSchedule?.let { emitSafely(it) }
            }
        }

    val availableCarParks: LiveData<AvailableCarParks> = liveData {
        observeAvailableCarParks(Unit).collect { carParks ->
            carParks?.let { emitSafely(it) }
        }
    }

    private suspend fun <T> LiveDataScope<T>.emitSafely(data: T) =
        try {
            _loading.value = true
            emit(data)
            _loading.value = false

        } catch (ex: Exception) {
            // Exception handling here
            _loading.value = false
        }

}