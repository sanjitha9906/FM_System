package com.example.fmsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fmsystem.models.Report

@Composable
fun ReportCard(report: Report) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Date: ${report.date}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Fetal Heart Rate: ${report.fetalHeartRate} bpm")
            Text(text = "Mother Heart Rate: ${report.motherHeartRate} bpm")
            Text(text = "Status: ${report.status}")
        }
    }
}