package com.example.fmsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.fmsystem.navigation.Routes

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Routes.DASHBOARD) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Dashboard,
                    contentDescription = "Dashboard"
                )
            },
            label = { Text("Dashboard") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Routes.MONITORING) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Monitoring"
                )
            },
            label = { Text("Monitoring") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Routes.REPORTS) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Assessment,
                    contentDescription = "Reports"
                )
            },
            label = { Text("Reports") }
        )
    }
}