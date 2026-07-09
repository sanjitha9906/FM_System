package com.example.fmsystem.screens.auth

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun SignupScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFE0F2FE), Color(0xFFF5F3FF), Color.White)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-60).dp, y = 50.dp)
                .background(Color(0xFF60A5FA).copy(alpha = 0.25f), CircleShape)
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
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.78f)),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Create Account",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172554)
                    )

                    Text(
                        text = "Start monitoring safely",
                        fontSize = 15.sp,
                        color = Color(0xFF64748B)
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            errorMessage = ""
                        },
                        label = { Text("Name") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = ""
                        },
                        label = { Text("Email") },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = ""
                        },
                        label = { Text("Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        singleLine = true
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

                    CustomButton("Signup") {
                        when {
                            name.isBlank() -> errorMessage = "Please enter your name"
                            email.isBlank() -> errorMessage = "Please enter your email"
                            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                                errorMessage = "Please enter a valid email address"
                            password.isBlank() -> errorMessage = "Please enter your password"
                            password.length < 6 -> errorMessage = "Password must be at least 6 characters"
                            else -> {
                                errorMessage = ""
                                navController.navigate(Routes.PATIENT_PROFILE)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextButton(onClick = { navController.navigate(Routes.LOGIN) }) {
                        Text(
                            text = "Already have an account? Login",
                            color = Color(0xFF2563EB)
                        )
                    }
                }
            }
        }
    }
}