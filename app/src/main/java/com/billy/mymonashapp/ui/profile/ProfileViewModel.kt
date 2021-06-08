package com.billy.mymonashapp.ui.profile

import androidx.lifecycle.*
import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.carpark.observer.ObserveAvailableCarParks
import com.billy.mymonashapp.domain.profile.StudentProfile
import com.billy.mymonashapp.domain.profile.observer.ObserveStudentProfile
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.observer.ObserveShuttleBusesSchedule
import com.billy.mymonashapp.ui.profile.model.CarPark
import com.billy.mymonashapp.ui.profile.model.Lecture
import com.billy.mymonashapp.ui.profile.model.ShuttleBus
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

    // Not ideal to expose internal states such as these in real-life scenario
    // I've done this only for convenient in order to randomise
    // arguably these are presentation logics and perhaps should sit in the view
    // and use espresso to test
    var lectureCount = 2
    var carParkCount = 2
    var busCount = 2

    // We can write a function to do randomising instead of repeating the code
    // I've decided to leave it due to time constraint
    val availableLectures: LiveData<List<Lecture>> =
        liveData {
            observeStudentProfile(Unit).collect { profile ->
                val lectures = mutableListOf<Lecture>()
                profile?.lectures?.map { lecture ->
                    if (lectures.size < lectureCount) {
                        lectures.add(mapToLecture(lecture))
                    }
                }

                emitSafely(lectures)
            }
        }

    val shuttleBusSchedule: LiveData<List<ShuttleBus>> =
        liveData {
            observeShuttleBusSchedule(Unit).collect { busSchedule ->
                val buses = mutableListOf<ShuttleBus>()
                busSchedule?.buses?.map { shuttleBus ->
                    if (buses.size < busCount) {
                        buses.add(mapToShuttleBus(shuttleBus))
                    }
                }

                emitSafely(buses)
            }
        }

    val availableCarParks: LiveData<List<CarPark>> =
        liveData {
            observeAvailableCarParks(Unit).collect { carParks ->
                val carparks = mutableListOf<CarPark>()
                carParks?.parkings?.map { carPark ->
                    if (carparks.size < carParkCount) {
                        carparks.add(mapToCarParkInfo(carPark))
                    }
                }

                emitSafely(carparks)
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