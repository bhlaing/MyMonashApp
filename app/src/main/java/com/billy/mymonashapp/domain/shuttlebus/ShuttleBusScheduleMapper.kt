package com.billy.mymonashapp.domain.shuttlebus

import com.billy.mymonashapp.data.shuttlebus.ShuttleBusScheduleDO

fun mapToShuttleBusSchedule(scheduleDO: ShuttleBusScheduleDO) =
    ShuttleBusSchedule(scheduleDO.buses.map {
        // !! because we have default values for data objects
        ShuttleBusSchedule.ShuttleBus(
            it.from!!,
            it.to!!,
            it.duration!!
        )
    })