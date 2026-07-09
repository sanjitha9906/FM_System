package com.example.fmsystem.screens.auth

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fmsystem.components.CustomButton
import com.example.fmsystem.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE0F2FE),
                        Color(0xFFF5F3FF),
                        Color.White
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(180.dp)
                .offset(x = (-50).dp, y = 40.dp)
                .background(Color(0xFF60A5FA).copy(alpha = 0.25f), CircleShape)
                .blur(40.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 40.dp, y = (-80).dp)
                .background(Color(0xFF14B8A6).copy(alpha = 0.20f), CircleShape)
                .blur(45.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.78f)
                ),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color(0xFFE0F2FE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Welcome Back",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172554)
                    )

                    Text(
                        text = "Fetal Monitoring System",
                        fontSize = 15.sp,
                        color = Color(0xFF64748B)
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = ""
                        },
                        label = { Text("Email") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2563EB),
                            unfocusedBorderColor = Color(0xFFCBD5E1),
                            focusedLabelColor = Color(0xFF2563EB)
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = ""
                        },
                        label = { Text("Password") },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2563EB),
                            unfocusedBorderColor = Color(0xFFCBD5E1),
                            focusedLabelColor = Color(0xFF2563EB)
                        )
                    )

                    if (errorMessage.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = errorMessage,
                            color = Color(0xFFDC2626),
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    CustomButton("Login") {
                        when {
                            email.isBlank() -> {
                                errorMessage = "Please enter your email"
                            }

                            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                                errorMessage = "Please enter a valid email address"
                            }

                            password.isBlank() -> {
                                errorMessage = "Please enter your password"
                            }

                            password.length < 6 -> {
                                errorMessage = "Password must be at least 6 characters"
                            }

                            else -> {
                                errorMessage = ""
                                navController.navigate(Routes.PATIENT_PROFILE)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextButton(onClick = { navController.navigate(Routes.SIGNUP) }) {
                        Text(
                            text = "Don't have an account? Signup",
                            color = Color(0xFF2563EB)
                        )
                    }

                    TextButton(onClick = { navController.navigate(Routes.FORGOT_PASSWORD) }) {
                        Text(
                            text = "Forgot Password?",
                            color = Color(0xFF0F766E)
                        )
                    }
                }
            }
        }
    }
}