package com.example.fmsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeartRateCard(
    title: String,
    value: String,
    unit: String
) {
    val cardColor: Color
    val icon: ImageVector

    when {
        title.contains("Fetal", true) -> {
            cardColor = Color(0xFFE91E63)
            icon = Icons.Default.Favorite
        }

        title.contains("Mother", true) -> {
            cardColor = Color(0xFF1976D2)
            icon = Icons.Default.MonitorHeart
        }

        title.contains("Head", true) -> {
            cardColor = Color(0xFF00897B)
            icon = Icons.Default.Straighten
        }

        title.contains("Movement", true) -> {
            cardColor = Color(0xFFFF9800)
            icon = Icons.Default.DirectionsWalk
        }

        else -> {
            cardColor = Color(0xFF6A1B9A)
            icon = Icons.Default.ChildCare
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(cardColor.copy(alpha = 0.13f), CircleShape)
                    .border(2.dp, cardColor.copy(alpha = 0.35f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = cardColor,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "$value $unit",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = cardColor
                )
            }
        }
    }
}