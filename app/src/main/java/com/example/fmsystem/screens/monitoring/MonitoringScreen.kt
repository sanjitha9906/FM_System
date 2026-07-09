package com.example.fmsystem.screens.monitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.PregnantWoman
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.viewmodel.MonitoringViewModel

@Composable
fun MonitoringScreen(
    navController: NavController,
    monitoringViewModel: MonitoringViewModel = viewModel()
) {
    val data by monitoringViewModel.data.collectAsState()
    val isConnected by monitoringViewModel.isConnected.collectAsState()
    val status = monitoringViewModel.getHealthStatus(data)

    LaunchedEffect(Unit) {
        monitoringViewModel.connectWebSocket()
    }

    val statusColor = when {
        status == "Normal" -> Color(0xFF16A34A)
        status.contains("Waiting", true) -> Color(0xFFF59E0B)
        else -> Color(0xFFDC2626)
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        containerColor = Color(0xFFF6F8FC)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFEAF4FF),
                            Color(0xFFFDF7FF),
                            Color.White
                        )
                    )
                )
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                text = "Live Monitoring",
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172554)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Real-time fetal health readings",
                fontSize = 15.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF172554)),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(modifier = Modifier.padding(22.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Current Outcome",
                                color = Color.White.copy(alpha = 0.75f),
                                fontSize = 14.sp
                            )

                            Text(
                                text = status,
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(statusColor, RoundedCornerShape(50.dp))
                                .padding(horizontal = 12.dp, vertical = 7.dp)
                        ) {
                            Text(
                                text = if (isConnected) "LIVE" else "OFFLINE",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    LinearProgressIndicator(
                        progress = if (isConnected) 1f else 0.25f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = statusColor,
                        trackColor = Color.White.copy(alpha = 0.2f)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isConnected)
                            "Jetson Nano connected and streaming data"
                        else
                            "Waiting for Jetson Nano live data",
                        color = Color.White.copy(alpha = 0.75f),
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                PremiumVitalCard(
                    title = "Fetal HR",
                    value = data.fetalHeartRate.toString(),
                    unit = "bpm",
                    icon = Icons.Default.Favorite,
                    color = Color(0xFFE91E63),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(14.dp))

                PremiumVitalCard(
                    title = "Mother HR",
                    value = data.motherHeartRate.toString(),
                    unit = "bpm",
                    icon = Icons.Default.MonitorHeart,
                    color = Color(0xFF2563EB),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                PremiumVitalCard(
                    title = "Head Circ.",
                    value = data.headCircumference.toString(),
                    unit = "cm",
                    icon = Icons.Default.Straighten,
                    color = Color(0xFF0F766E),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(14.dp))

                PremiumVitalCard(
                    title = "Movement",
                    value = data.movementCount.toString(),
                    unit = "moves",
                    icon = Icons.Default.PregnantWoman,
                    color = Color(0xFFF59E0B),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun PremiumVitalCard(
    title: String,
    value: String,
    unit: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(160.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(color.copy(alpha = 0.13f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color
                )
            }

            Column {
                Text(
                    text = title,

                    color = Color(0xFF64748B),
                    fontSize = 14.sp
                )

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = value,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = unit,
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            }
        }
    }
}