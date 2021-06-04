package com.billy.mymonashapp.domain.shuttlebus

import com.billy.mymonashapp.data.shuttlebus.ShuttleBusScheduleDO

fun mapToShuttleBusSchedule(scheduleDO: ShuttleBusScheduleDO) =
    ShuttleBusSchedule(scheduleDO.buses.map {
        ShuttleBusSchedule.ShuttleBus(
            it.from,
            it.to,
            it.duration
        )
    })