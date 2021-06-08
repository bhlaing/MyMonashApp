package com.billy.mymonashapp.domain

import com.billy.mymonashapp.BaseTest
import com.billy.mymonashapp.domain.builders.buildShuttleBus
import com.billy.mymonashapp.domain.builders.buildShuttleBusSchedule
import com.billy.mymonashapp.domain.shuttlebus.mapToShuttleBusSchedule
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ShuttleBusScheduleMapperTest : BaseTest() {
    private val busSchedule = buildShuttleBusSchedule(
        listOf(
            buildShuttleBus(from = "Clayton", to = "Caufield", duration = "4 mins"),
            buildShuttleBus(from = "Frankston", to = "Dandenong", duration = "5 mins")
        )
    )

    @Test
    fun `given a list of shuttle bus schedule data, when mapping, then maps to shuttle bus schedule`() {
        val buses = mapToShuttleBusSchedule(busSchedule).buses
        assertTrue(buses.size == 2)

        with(buses.first()) {

            assertEquals("Clayton", from)
            assertEquals("Caufield", to)
            assertEquals("4 mins", duration)
        }

        with(buses.component2()) {

            assertEquals("Frankston", from)
            assertEquals("Dandenong", to)
            assertEquals("5 mins", duration)
        }
    }
}