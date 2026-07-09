package com.example.fmsystem.screens.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fmsystem.components.CustomButton
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgetPassword(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = androidx.compose.ui.platform.LocalContext.current
    val auth = FirebaseAuth.getInstance()

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
                .offset(x = (-50).dp, y = 60.dp)
                .background(Color(0xFF14B8A6).copy(alpha = 0.22f), CircleShape)
                .blur(40.dp)
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
                    Icon(
                        imageVector = Icons.Default.LockReset,
                        contentDescription = null,
                        tint = Color(0xFF2563EB),
                        modifier = Modifier.size(54.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Reset Password",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172554)
                    )

                    Text(
                        text = "Enter your registered email",
                        fontSize = 15.sp,
                        color = Color(0xFF64748B)
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            message = ""
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

                    if (message.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = message,
                            color = if (message.contains("sent", true))
                                Color(0xFF16A34A)
                            else
                                Color(0xFFDC2626),
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    CustomButton(
                        text = if (isLoading) "Sending..." else "Send Reset Link"
                    ) {
                        when {
                            email.isBlank() -> {
                                message = "Please enter your email"
                            }

                            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                                message = "Please enter a valid email address"
                            }

                            else -> {
                                isLoading = true
                                message = ""

                                auth.sendPasswordResetEmail(email)
                                    .addOnSuccessListener {
                                        isLoading = false
                                        message = "Reset link sent to your email"

                                        Toast.makeText(
                                            context,
                                            "Password reset link sent successfully",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    .addOnFailureListener { exception ->
                                        isLoading = false
                                        message = exception.message
                                            ?: "Failed to send reset link"
                                    }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text(
                            text = "Back to Login",
                            color = Color(0xFF2563EB)
                        )
                    }
                }
            }
        }
    }
}