package com.billy.mymonashapp.ui

import com.billy.mymonashapp.BaseCoroutinesTest
import com.billy.mymonashapp.data.carpark.buildAvailableCarParks
import com.billy.mymonashapp.data.carpark.buildCarPark
import com.billy.mymonashapp.data.lecture.buildLecture
import com.billy.mymonashapp.domain.builders.buildShuttleBus
import com.billy.mymonashapp.data.lecture.buildStudentLecture
import com.billy.mymonashapp.domain.builders.buildShuttleBusSchedule
import com.billy.mymonashapp.domain.carpark.AvailableCarParks
import com.billy.mymonashapp.domain.carpark.mapToAvailableCarParks
import com.billy.mymonashapp.domain.carpark.observer.ObserveAvailableCarParks
import com.billy.mymonashapp.domain.lecture.StudentLecture
import com.billy.mymonashapp.domain.lecture.mapToStudentProfile
import com.billy.mymonashapp.domain.lecture.observer.ObserveStudentLectures
import com.billy.mymonashapp.domain.shuttlebus.ShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.mapToShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.observer.ObserveShuttleBusesSchedule
import com.billy.mymonashapp.getOrAwaitValue
import com.billy.mymonashapp.ui.home.ProfileViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class ProfileViewModelTest : BaseCoroutinesTest() {

    private lateinit var profileViewModel: ProfileViewModel

    @Mock
    private lateinit var observeStudentLectures: ObserveStudentLectures

    @Mock
    private lateinit var observeShuttleBusSchedule: ObserveShuttleBusesSchedule

    @Mock
    private lateinit var observeAvailableCarParks: ObserveAvailableCarParks

    private lateinit var mockLecture: StudentLecture
    private lateinit var mockBuses: ShuttleBusSchedule
    private lateinit var mockCarparks: AvailableCarParks

    @Before
    override fun setUp() {
        super.setUp()

        // reset each of these to default to avoid coupling between tests
        mockLecture = mapToStudentProfile(buildStudentLecture())
        mockBuses = mapToShuttleBusSchedule(buildShuttleBusSchedule())
        mockCarparks = mapToAvailableCarParks(buildAvailableCarParks())

        runBlocking {
            whenever(observeStudentLectures.invoke(Unit)).thenReturn(flow { emit(mockLecture) })
            whenever(observeShuttleBusSchedule.invoke(Unit)).thenReturn(flow { emit(mockBuses) })
            whenever(observeAvailableCarParks.invoke(Unit)).thenReturn(flow { emit(mockCarparks) })
        }

        profileViewModel = ProfileViewModel(
            observeStudentLectures,
            observeShuttleBusSchedule,
            observeAvailableCarParks
        )


    }

    @Test
    fun `given a user profile, when valid lectures present, then display lectures count according to randomised number`() {
        mockLecture = mapToStudentProfile(buildStudentLecture())

        runBlocking {
            profileViewModel = ProfileViewModel(
                observeStudentLectures,
                observeShuttleBusSchedule,
                observeAvailableCarParks
            )
            profileViewModel.lectureCount = 2

            with(profileViewModel.availableLectures.getOrAwaitValue()) {
                assertTrue(2 == size)
            }
        }
    }

    @Test
    fun `given a user profile, when valid lectures present, then display lectures with correct information`() {
        runBlocking {
            mockLecture = mapToStudentProfile(
                buildStudentLecture(
                    listOf(
                        buildLecture("1", "11", "name1", "lecture1", "info1"),
                        buildLecture("2", "22", "name2", "lecturer2", "info2"),
                        buildLecture("3", "33", "name3", "lecturer3", "info3")
                    )
                )
            )

            whenever(observeStudentLectures.invoke(Unit)).thenReturn(flow { emit(mockLecture) })

            profileViewModel = ProfileViewModel(
                observeStudentLectures,
                observeShuttleBusSchedule,
                observeAvailableCarParks
            ).apply { lectureCount = 2 }

            with(profileViewModel.availableLectures.getOrAwaitValue()) {
                assertEquals("1", first().fromTime)
                assertEquals("11", first().toTime)
                assertEquals("name1", first().name)
                assertEquals("lecture1", first().lecturer)
                assertEquals("info1", first().campusInfoString)

                assertEquals("2", component2().fromTime)
                assertEquals("22", component2().toTime)
                assertEquals("name2", component2().name)
                assertEquals("lecturer2", component2().lecturer)
                assertEquals("info2", component2().campusInfoString)
            }
        }
    }

    @Test
    fun `given a user, when carparks are available, then update car parks count according to randomised number`() {
        runBlocking {
            mockCarparks = mapToAvailableCarParks(
                buildAvailableCarParks(
                    listOf(
                        buildCarPark(),
                        buildCarPark(),
                        buildCarPark()
                    )
                )
            )

            whenever(observeAvailableCarParks.invoke(Unit)).thenReturn(flow { emit(mockCarparks) })

            profileViewModel = ProfileViewModel(
                observeStudentLectures,
                observeShuttleBusSchedule,
                observeAvailableCarParks
            ).apply { carParkCount = 3 }

            with(profileViewModel.availableCarParksItem.getOrAwaitValue()) {
                assertTrue(3 == size)
            }
        }
    }

    @Test
    fun `given a user, when carparks are available, then update correct carpark information`() {
        runBlocking {
            mockCarparks = mapToAvailableCarParks(
                buildAvailableCarParks(
                    listOf(
                        buildCarPark("name", "11"),
                        buildCarPark("name2", "22"),
                        buildCarPark("name3", "33")
                    )
                )
            )

            whenever(observeAvailableCarParks.invoke(Unit)).thenReturn(flow { emit(mockCarparks) })

            profileViewModel = ProfileViewModel(
                observeStudentLectures,
                observeShuttleBusSchedule,
                observeAvailableCarParks
            ).apply { carParkCount = 3 }

            with(profileViewModel.availableCarParksItem.getOrAwaitValue()) {
                assertEquals("name", first().carparkName)
                assertEquals("11", first().availableSpaces)

                assertEquals("name2", component2().carparkName)
                assertEquals("22", component2().availableSpaces)

                assertEquals("name3", component3().carparkName)
                assertEquals("33", component3().availableSpaces)
            }
        }
    }

    @Test
    fun `given a user, when inter campus shuttle buses are available, then update shuttle bus count according to randomised number`() {
        runBlocking {
            mockBuses = mapToShuttleBusSchedule(
                buildShuttleBusSchedule(
                    listOf(
                        buildShuttleBus(),
                        buildShuttleBus(),
                        buildShuttleBus()
                    )
                )
            )

            whenever(observeShuttleBusSchedule.invoke(Unit)).thenReturn(flow { emit(mockBuses) })

            profileViewModel = ProfileViewModel(
                observeStudentLectures,
                observeShuttleBusSchedule,
                observeAvailableCarParks
            ).apply { busCount = 1 }

            with(profileViewModel.availableCarParksItem.getOrAwaitValue()) {
                assertTrue(1 == size)
            }
        }
    }

    @Test
    fun `given a user, when inter campus shuttle buses are available, then update to correct shuttle bus information`() {
        runBlocking {
            mockBuses = mapToShuttleBusSchedule(
                buildShuttleBusSchedule(
                    listOf(
                        buildShuttleBus("Clay", "ton", "21 mins"),
                        buildShuttleBus(),
                        buildShuttleBus()
                    )
                )
            )

            whenever(observeShuttleBusSchedule.invoke(Unit)).thenReturn(flow { emit(mockBuses) })

            profileViewModel = ProfileViewModel(
                observeStudentLectures,
                observeShuttleBusSchedule,
                observeAvailableCarParks
            ).apply { busCount = 1 }

            with(profileViewModel.shuttleBusItemSchedule.getOrAwaitValue()) {
                assertEquals("Clay", first().from)
                assertEquals("ton", first().to)
                assertEquals("21 mins", first().duration)
            }
        }
    }
}