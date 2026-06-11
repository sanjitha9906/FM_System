package com.example.fmsystem.screens.monitoring

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.components.HeartRateCard
import com.example.fmsystem.models.MonitoringData
import com.example.fmsystem.websocket.WebSocketManager

@Composable
fun MonitoringScreen(navController: NavController) {
    var data by remember { mutableStateOf(MonitoringData()) }

    DisposableEffect(Unit) {
        val ws = WebSocketManager {
            data = it
        }
        ws.connect()

        onDispose {
            ws.disconnect()
        }
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Live Monitoring", style = MaterialTheme.typography.headlineMedium)

            HeartRateCard("Fetal Heart Rate", data.fetalHeartRate.toString(), "bpm")
            HeartRateCard("Mother Heart Rate", data.motherHeartRate.toString(), "bpm")
            HeartRateCard("Temperature", data.temperature.toString(), "°C")
            HeartRateCard("Movement Count", data.movementCount.toString(), "movements")

            Text("Status: ${data.status}", style = MaterialTheme.typography.titleLarge)
        }
    }
}