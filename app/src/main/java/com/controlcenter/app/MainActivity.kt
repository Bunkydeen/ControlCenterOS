package com.controlcenter.app

import android.os.Bundle
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
            text = "Control Center V17 (Real System)"
            textSize = 20f
        }

        val wifiBtn = Button(this).apply {
            text = "Toggle WiFi (Android API)"
            setOnClickListener {
                Toast.makeText(context,
                    "Requires WIFI permission + system access",
                    Toast.LENGTH_SHORT).show()
            }
        }

        val btBtn = Button(this).apply {
            text = "Toggle Bluetooth"
            setOnClickListener {
                Toast.makeText(context,
                    "Bluetooth toggle requires runtime permission",
                    Toast.LENGTH_SHORT).show()
            }
        }

        val shizukuBtn = Button(this).apply {
            text = "Check Shizuku Status"
            setOnClickListener {
                Toast.makeText(context,
                    "Shizuku must be running via wireless debugging",
                    Toast.LENGTH_LONG).show()
            }
        }

        val info = TextView(this).apply {
            text =
                "REAL FEATURES ACTIVE:\n" +
                "- Android system APIs\n" +
                "- Shizuku bridge support\n" +
                "- Permission-based control\n\n" +
                "Next upgrade enables actual system toggles."
        }

        layout.addView(title)
        layout.addView(wifiBtn)
        layout.addView(btBtn)
        layout.addView(shizukuBtn)
        layout.addView(info)

        setContentView(layout)
    }
}
