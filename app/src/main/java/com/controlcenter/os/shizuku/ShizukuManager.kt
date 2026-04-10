package com.controlcenter.os.shizuku

import android.content.Context
import dev.rikka.shizuku.Shizuku

object ShizukuManager {

    fun isShizukuAvailable(): Boolean {
        return Shizuku.pingBinder()
    }

    fun hasPermission(): Boolean {
        return Shizuku.checkSelfPermission() == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {
        Shizuku.requestPermission(1001)
    }
}
