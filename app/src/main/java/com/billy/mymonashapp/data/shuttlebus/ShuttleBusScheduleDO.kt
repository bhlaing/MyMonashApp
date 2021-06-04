package com.billy.mymonashapp.data.shuttlebus

class ShuttleBusScheduleDO(val buses: List<ShuttleBusDO>) {
    class ShuttleBusDO(
        val from: String,
        val to: String,
        val duration: String
    )
}