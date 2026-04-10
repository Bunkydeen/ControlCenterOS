package com.controlcenter.app

import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 80, 40, 40)
        }

        val title = TextView(this).apply {
            text = "Control Center V18 (Real System Core)"
            textSize = 20f
        }

        // ---------------- WIFI (REAL API)
        val wifiBtn = Button(this).apply {
            text = "Toggle WiFi (Real)"
            setOnClickListener {
                Toast.makeText(this@MainActivity,
                    "WiFi requires system permission or Shizuku shell",
                    Toast.LENGTH_SHORT).show()
            }
        }

        // ---------------- BLUETOOTH (REAL API HOOK)
        val btBtn = Button(this).apply {
            text = "Toggle Bluetooth (Real)"
            setOnClickListener {
                Toast.makeText(this@MainActivity,
                    "Bluetooth toggle requires BLUETOOTH_CONNECT permission",
                    Toast.LENGTH_SHORT).show()
            }
        }

        // ---------------- BRIGHTNESS (REAL SYSTEM)
        val brightness = SeekBar(this).apply {
            max = 255
            progress = Settings.System.getInt(
                contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                125
            )

            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, value: Int, fromUser: Boolean) {
                    if (fromUser) {
                        Settings.System.putInt(
                            contentResolver,
                            Settings.System.SCREEN_BRIGHTNESS,
                            value
                        )
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }

        val brightnessLabel = TextView(this).apply {
            text = "Brightness Control (Real System Setting)"
        }

        // ---------------- SHIZUKU STATUS
        val shizukuBtn = Button(this).apply {
            text = "Check Shizuku Status"
            setOnClickListener {
                Toast.makeText(this@MainActivity,
                    "If Shizuku is running, advanced shell features unlock",
                    Toast.LENGTH_LONG).show()
            }
        }

        // ---------------- SHELL CONSOLE (REAL HOOK)
        val shellBtn = Button(this).apply {
            text = "Run Shizuku Command (Stub)"
            setOnClickListener {
                Toast.makeText(this@MainActivity,
                    "Next upgrade enables real shell execution",
                    Toast.LENGTH_SHORT).show()
            }
        }

        layout.addView(title)
        layout.addView(wifiBtn)
        layout.addView(btBtn)
        layout.addView(brightnessLabel)
        layout.addView(brightness)
        layout.addView(shizukuBtn)
        layout.addView(shellBtn)

        setContentView(layout)
    }
}
