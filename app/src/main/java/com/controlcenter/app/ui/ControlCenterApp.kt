package com.controlcenter.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.controlcenter.app.state.ControlState

@Composable
fun ControlCenterApp() {

    val toggles = ControlState.toggles

    val items = toggles.keys.toList()

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Text("ControlCenterOS V11", style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { key ->
                    val state = toggles[key] ?: false

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (state)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier
                            .height(100.dp)
                            .clickable { ControlState.toggle(key) }
                    ) {
                        Box(Modifier.padding(12.dp)) {
                            Text("$key: ${if (state) "ON" else "OFF"}")
                        }
                    }
                }
            }
        }
    }
}
