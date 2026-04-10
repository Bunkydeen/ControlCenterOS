package com.controlcenter.os.shizuku

import android.content.pm.PackageManager
import rikka.shizuku.Shizuku

object ShizukuManager {

    fun isAvailable(): Boolean {
        return try {
            Shizuku.pingBinder()
        } catch (e: Exception) {
            false
        }
    }

    fun hasPermission(): Boolean {
        return try {
            Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) {
            false
        }
    }

    fun requestPermission() {
        try {
            Shizuku.requestPermission(1001)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
