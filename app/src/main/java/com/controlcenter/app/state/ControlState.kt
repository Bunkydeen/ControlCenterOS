package com.controlcenter.app.state

import androidx.compose.runtime.mutableStateMapOf

object ControlState {
    val toggles = mutableStateMapOf(
        "WiFi" to false,
        "Bluetooth" to false,
        "Airplane" to false,
        "DarkMode" to false,
        "GPS" to false,
        "Sync" to false
    )

    fun toggle(key: String) {
        toggles[key] = !(toggles[key] ?: false)
    }
}
