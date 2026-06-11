package com.example.fmsystem.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.components.HeartRateCard

@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)

            HeartRateCard("Fetal Heart Rate", "140", "bpm")
            HeartRateCard("Mother Heart Rate", "82", "bpm")
            HeartRateCard("Temperature", "36.8", "°C")
            HeartRateCard("Movement Count", "12", "movements")
        }
    }
}