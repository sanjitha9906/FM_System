package com.example.fmsystem.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fmsystem.components.BottomNavBar
import com.example.fmsystem.repository.PatientRepository

@Composable
fun ProfileScreen(navController: NavController) {

    val patient = PatientRepository.patient.value

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        },
        containerColor = Color.Transparent
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFE3F2FD),
                            Color.White
                        )
                    )
                )
                .padding(padding)
                .padding(20.dp)
        ) {

            Text(
                text = "My Profile",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.75f)
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Surface(
                        modifier = Modifier.size(90.dp),
                        shape = CircleShape,
                        color = Color(0xFF2563EB)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(18.dp)
                        )

                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = patient.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Patient",
                        color = Color.Gray
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            ProfileItem("Age", patient.age)
            ProfileItem("Blood Group", patient.bloodGroup)
            ProfileItem("Pregnancy Week", patient.pregnancyWeek)
            ProfileItem("Doctor", patient.doctorName)
            ProfileItem("Phone", patient.phone)
            ProfileItem("Emergency", patient.emergencyContact)
            ProfileItem("Address", patient.address)

            ProfileItem(
                "Medical History",
                patient.medicalHistory.joinToString(", ")
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Edit Profile")
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Settings")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("login")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Logout")

            }

        }

    }

}

@Composable
fun ProfileItem(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.75f)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold
            )

            Text(text = value)

        }

    }

}