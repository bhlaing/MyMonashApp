package com.billy.mymonashapp.domain

import com.billy.mymonashapp.BaseTest
import com.billy.mymonashapp.data.carpark.buildAvailableCarParks
import com.billy.mymonashapp.data.carpark.buildCarPark
import com.billy.mymonashapp.domain.carpark.mapToAvailableCarParks
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CarParkMapperTest : BaseTest() {

    @Test
    fun `given a list of carpark data availability information, when mapping, maps to carpark`() {
        val carParks =
            buildAvailableCarParks(listOf(buildCarPark("a", "1"), buildCarPark("b", "2")))

        with(mapToAvailableCarParks(carParks).parkings) {
            assertTrue(this.size == 2)

            assertEquals("a", this.first().carparkName)
            assertEquals("1", this.first().availableSpaces)

            assertEquals("b", this.component2().carparkName)
            assertEquals("2", this.component2().availableSpaces)
        }
    }
}