package com.example.fmsystem.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fmsystem.screens.auth.ForgetPassword
import com.example.fmsystem.screens.auth.LoginScreen
import com.example.fmsystem.screens.auth.SignupScreen
import com.example.fmsystem.screens.dashboard.DashboardScreen
import com.example.fmsystem.screens.monitoring.MonitoringScreen
import com.example.fmsystem.screens.profile.PatientProfileScreen
import com.example.fmsystem.screens.reports.ReportsScreen
import com.example.fmsystem.screens.profile.ProfileScreen
import com.example.fmsystem.screens.settings.SettingsScreen
import com.example.fmsystem.screens.splash.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val FORGOT_PASSWORD = "forgot_password"
    const val PATIENT_PROFILE = "patient_profile"
    const val DASHBOARD = "dashboard"
    const val MONITORING = "monitoring"
    const val REPORTS = "reports"

    const val PROFILE = "profile"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.SIGNUP) {
            SignupScreen(navController)
        }

        composable(Routes.FORGOT_PASSWORD) {
            ForgetPassword(navController)
        }

        composable(Routes.PATIENT_PROFILE) {
            PatientProfileScreen(navController)
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(Routes.MONITORING) {
            MonitoringScreen(navController)
        }

        composable(Routes.REPORTS) {
            ReportsScreen(navController)
        }

        composable(Routes.PROFILE) {
            ProfileScreen(navController)
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(navController)
        }
    }
}