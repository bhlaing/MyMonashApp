package com.billy.mymonashapp.domain.builders

import com.billy.mymonashapp.data.shuttlebus.ShuttleBusScheduleDO

fun buildShuttleBusSchedule(
    busSchedules: List<ShuttleBusScheduleDO.ShuttleBusDO> = listOf(
        buildShuttleBus(),
        buildShuttleBus()
    )
) = ShuttleBusScheduleDO(busSchedules)

fun buildShuttleBus(
    from: String = "Clayton",
    to: String = "Caufield",
    duration: String = "4 mins"
) = ShuttleBusScheduleDO.ShuttleBusDO(from, to, duration)
