package com.example.fmsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fmsystem.navigation.Routes

@Composable
fun BottomNavBar(navController: NavController) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == Routes.DASHBOARD,
            onClick = {
                navController.navigate(Routes.DASHBOARD) {
                    popUpTo(Routes.DASHBOARD) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Dashboard,
                    contentDescription = "Dashboard"
                )
            },
            label = { Text("Dashboard") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.MONITORING,
            onClick = {
                navController.navigate(Routes.MONITORING) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Monitoring"
                )
            },
            label = { Text("Monitoring") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.REPORTS,
            onClick = {
                navController.navigate(Routes.REPORTS) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Assessment,
                    contentDescription = "Reports"
                )
            },
            label = { Text("Reports") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.PROFILE,
            onClick = {
                navController.navigate(Routes.PROFILE) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") }
        )

    }
}