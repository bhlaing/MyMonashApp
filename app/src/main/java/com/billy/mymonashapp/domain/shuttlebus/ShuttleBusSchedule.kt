package com.billy.mymonashapp.domain.shuttlebus

// this is var due to randomising requirement
// in real-life scenario we want to make this a val
// and map to a presentation model instead
class ShuttleBusSchedule(var buses: List<ShuttleBus>) {
    class ShuttleBus(
        val from: String,
        val to: String,
        val duration: String
    )
}