package com.example.fmsystem.screens.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.components.ReportCard
import com.example.fmsystem.models.Report

@Composable
fun ReportsScreen(navController: NavController) {

    val reports = listOf(

        Report(
            id = 1,
            date = "12 July 2026",
            fetalHeartRate = 142,
            motherHeartRate = 84,
            headCircumference = 34.8f,
            movementCount = 18,
            status = "Healthy"
        ),

        Report(
            id = 2,
            date = "11 July 2026",
            fetalHeartRate = 140,
            motherHeartRate = 82,
            headCircumference = 34.6f,
            movementCount = 15,
            status = "Healthy"
        ),

        Report(
            id = 3,
            date = "10 July 2026",
            fetalHeartRate = 151,
            motherHeartRate = 90,
            headCircumference = 35.0f,
            movementCount = 11,
            status = "Observation Required"
        )

    )

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        },
        containerColor = Color.Transparent
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFE0F2FE),
                            Color.White
                        )
                    )
                )
                .padding(padding)
        ) {

            Box(
                modifier = Modifier
                    .size(220.dp)
                    .offset((-60).dp, 20.dp)
                    .background(
                        Color(0xFF60A5FA).copy(alpha = 0.20f),
                        CircleShape
                    )
                    .blur(60.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Assessment,
                        contentDescription = null,
                        tint = Color(0xFF2563EB),
                        modifier = Modifier.size(36.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            text = "Reports",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Previous Monitoring Reports",
                            color = Color.Gray
                        )

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(reports) { report ->

                        ReportCard(
                            report = report,
                            onDownloadClick = {

                                // PDF Generator will be added next

                            }
                        )

                    }

                }

            }

        }

    }

}