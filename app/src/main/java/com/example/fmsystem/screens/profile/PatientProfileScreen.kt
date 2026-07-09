package com.example.fmsystem.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fmsystem.components.CustomButton
import com.example.fmsystem.models.Patient
import com.example.fmsystem.navigation.Routes
import com.example.fmsystem.viewmodel.PatientViewModel

@Composable
fun PatientProfileScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var pregnancyWeek by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var doctorName by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }
    var medicalHistory by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    val patientViewModel: PatientViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE0F2FE),
                        Color(0xFFF8FAFC),
                        Color.White
                    )
                )
            )
    ) {

        Box(
            modifier = Modifier
                .size(220.dp)
                .offset((-70).dp,30.dp)
                .background(
                    Color(0xFF60A5FA).copy(alpha = .20f),
                    CircleShape
                )
                .blur(45.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(22.dp)
        ) {

            Text(
                "Patient Profile",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172554)
            )

            Text(
                "Complete your details",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProfileSection("Personal Details") {

                GlassTextField(
                    name,
                    { name = it },
                    "Patient Name",
                    Icons.Default.Person
                )

                GlassTextField(
                    age,
                    { age = it },
                    "Age",
                    Icons.Default.CalendarMonth
                )

                GlassTextField(
                    phone,
                    { phone = it },
                    "Phone Number",
                    Icons.Default.Phone
                )

                GlassTextField(
                    address,
                    { address = it },
                    "Address",
                    Icons.Default.LocationOn
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileSection("Pregnancy Details") {

                GlassTextField(
                    pregnancyWeek,
                    { pregnancyWeek = it },
                    "Pregnancy Week",
                    Icons.Default.HealthAndSafety
                )

                GlassTextField(
                    bloodGroup,
                    { bloodGroup = it },
                    "Blood Group",
                    Icons.Default.Bloodtype
                )

                GlassTextField(
                    doctorName,
                    { doctorName = it },
                    "Doctor Name",
                    Icons.Default.LocalHospital
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileSection("Emergency Details") {

                GlassTextField(
                    emergencyContact,
                    { emergencyContact = it },
                    "Emergency Contact",
                    Icons.Default.Sos
                )

                GlassTextField(
                    medicalHistory,
                    { medicalHistory = it },
                    "Medical History",
                    Icons.Default.MedicalInformation
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            if(errorMessage.isNotEmpty()){

                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

            }


            var showError by remember { mutableStateOf(false) }

            CustomButton("Save and Continue") {

                if (
                    name.isBlank() ||
                    age.isBlank() ||
                    phone.isBlank() ||
                    address.isBlank() ||
                    pregnancyWeek.isBlank() ||
                    bloodGroup.isBlank() ||
                    doctorName.isBlank() ||
                    emergencyContact.isBlank() ||
                    medicalHistory.isBlank()
                ) {

                    showError = true
                    return@CustomButton
                }

                patientViewModel.savePatient(

                    Patient(
                        name = name,
                        age = age,
                        phone = phone,
                        address = address,
                        pregnancyWeek = pregnancyWeek,
                        bloodGroup = bloodGroup,
                        doctorName = doctorName,
                        emergencyContact = emergencyContact,
                        medicalHistory = mutableListOf(medicalHistory)
                    )

                )

                navController.navigate(Routes.DASHBOARD)

            }

            if (showError) {

                Text(
                    text = "Please fill all fields",
                    color = MaterialTheme.colorScheme.error

                )

            }

            {

                        errorMessage=""

                        patientViewModel.savePatient(

                            Patient(

                                name=name,
                                age=age,
                                phone=phone,
                                address=address,
                                pregnancyWeek=pregnancyWeek,
                                bloodGroup=bloodGroup,
                                doctorName=doctorName,
                                emergencyContact=emergencyContact,
                                medicalHistory=mutableListOf(medicalHistory)

                            )

                        )

                        navController.navigate(Routes.DASHBOARD)

                    }

                }

            }

            Spacer(modifier = Modifier.height(30.dp))

        }

@Composable
fun ProfileSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.80f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A)
            )

            Spacer(modifier = Modifier.height(12.dp))

            content()

        }

    }

}

@Composable
fun GlassTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector
) {

    OutlinedTextField(
        value = value,

        onValueChange = onValueChange,

        label = {
            Text(label)
        },

        leadingIcon = {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF2563EB)
            )

        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),

        shape = RoundedCornerShape(18.dp),

        singleLine = true,

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = Color.White.copy(alpha = 0.65f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.65f),

            focusedBorderColor = Color(0xFF2563EB),
            unfocusedBorderColor = Color(0xFFD1D5DB),

            focusedLabelColor = Color(0xFF2563EB),
            unfocusedLabelColor = Color.Gray,

            cursorColor = Color(0xFF2563EB)

        )

    )

}