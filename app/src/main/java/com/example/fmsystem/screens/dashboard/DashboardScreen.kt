package com.example.fmsystem.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.components.HeartRateCard
import com.example.fmsystem.viewmodel.MonitoringViewModel

@Composable
fun DashboardScreen(
    navController: NavController,
    monitoringViewModel: MonitoringViewModel = viewModel()
) {
    val monitoringData by monitoringViewModel.data.collectAsState()
    val isConnected by monitoringViewModel.isConnected.collectAsState()

    LaunchedEffect(Unit) {
        monitoringViewModel.connectWebSocket()
    }

    DisposableEffect(Unit) {
        onDispose {
            monitoringViewModel.disconnectWebSocket()
        }
    }

    val status = monitoringViewModel.getHealthStatus(monitoringData)

    val statusColor = when {
        status == "Normal" -> Color(0xFF2E7D32)
        status.contains("Waiting", true) -> Color(0xFFFF9800)
        else -> Color(0xFFD32F2F)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        },
        containerColor = Color(0xFFF8F9FD)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFFFFFFF)
                        )
                    )
                )
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Fetal Monitor",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "Dashboard",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A237E)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                if (isConnected) Color(0xFF2E7D32) else Color(0xFFD32F2F),
                                CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = if (isConnected) "Connected" else "Offline",
                        fontSize = 13.sp,
                        color = if (isConnected) Color(0xFF2E7D32) else Color(0xFFD32F2F)
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = statusColor
                ),
                shape = RoundedCornerShape(26.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Current Outcome",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = status,
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Live readings received from Jetson Nano",
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            HeartRateCard(
                title = "Fetal Heart Rate",
                value = monitoringData.fetalHeartRate.toString(),
                unit = "bpm"
            )

            HeartRateCard(
                title = "Mother Heart Rate",
                value = monitoringData.motherHeartRate.toString(),
                unit = "bpm"
            )

            HeartRateCard(
                title = "Head Circumference",
                value = monitoringData.headCircumference.toString(),
                unit = "cm"
            )

            HeartRateCard(
                title = "Movement Count",
                value = monitoringData.movementCount.toString(),
                unit = "moves"
            )
        }
    }
}