package com.example.fmsystem.screens.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.components.ReportCard
import com.example.fmsystem.models.Report

@Composable
fun ReportsScreen(navController: NavController) {
    val reports = listOf(
        Report(1, "2026-06-10", 140, 82, "Normal"),
        Report(2, "2026-06-09", 148, 86, "Normal"),
        Report(3, "2026-06-08", 160, 90, "Check Required")
    )

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Reports", style = MaterialTheme.typography.headlineMedium)

            reports.forEach {
                ReportCard(report = it)
            }
        }
    }
}