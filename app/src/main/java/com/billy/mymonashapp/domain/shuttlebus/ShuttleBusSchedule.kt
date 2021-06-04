package com.billy.mymonashapp.domain.shuttlebus

class ShuttleBusSchedule(val buses: List<ShuttleBus>) {
    class ShuttleBus(
        val from: String,
        val to: String,
        val duration: String
    )
}