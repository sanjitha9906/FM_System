package com.example.fmsystem.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fmsystem.components.CustomButton
import com.example.fmsystem.navigation.Routes

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Patient Details", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Patient Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Age") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = pregnancyWeek, onValueChange = { pregnancyWeek = it }, label = { Text("Pregnancy Week") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = bloodGroup, onValueChange = { bloodGroup = it }, label = { Text("Blood Group") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = doctorName, onValueChange = { doctorName = it }, label = { Text("Doctor Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = emergencyContact, onValueChange = { emergencyContact = it }, label = { Text("Emergency Contact") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = medicalHistory, onValueChange = { medicalHistory = it }, label = { Text("Medical History") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton("Save and Continue") {
            navController.navigate(Routes.DASHBOARD)
        }
    }
}