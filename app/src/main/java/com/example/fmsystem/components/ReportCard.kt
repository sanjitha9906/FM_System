package com.example.fmsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.PregnantWoman
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fmsystem.models.Report

@Composable
fun ReportCard(
    report: Report,
    onDownloadClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.80f)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFE0F2FE),
                            Color.White
                        )
                    )
                )
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Report #${report.id}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = report.date,
                        color = Color.Gray
                    )

                }

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color =
                        if (report.status == "Healthy")
                            Color(0xFF4CAF50)
                        else
                            Color(0xFFFF9800)
                ) {

                    Text(
                        text = report.status,
                        color = Color.White,
                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 6.dp
                        )
                    )

                }

            }

            Spacer(modifier = Modifier.height(18.dp))

            ReportItem(
                Icons.Default.Favorite,
                "Fetal Heart Rate",
                "${report.fetalHeartRate} bpm"
            )

            ReportItem(
                Icons.Default.MonitorHeart,
                "Mother Heart Rate",
                "${report.motherHeartRate} bpm"
            )

            ReportItem(
                Icons.Default.PregnantWoman,
                "Head Circumference",
                "${report.headCircumference} cm"
            )

            ReportItem(
                Icons.Default.DirectionsWalk,
                "Movement Count",
                "${report.movementCount}"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onDownloadClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Icon(
                    Icons.Default.Download,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Download PDF")

            }

        }

    }

}

@Composable
fun ReportItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF2563EB)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = value,
                color = Color.Gray
            )

        }

    }

}