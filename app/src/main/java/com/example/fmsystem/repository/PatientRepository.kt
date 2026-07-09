package com.example.fmsystem.repository

import androidx.compose.runtime.mutableStateOf
import com.example.fmsystem.models.Patient

object PatientRepository {

    val patient = mutableStateOf(Patient())

    fun savePatient(patientData: Patient) {
        patient.value = patientData
    }

    fun updatePhone(phone: String) {
        patient.value = patient.value.copy(
            phone = phone
        )
    }

    fun updateAddress(address: String) {
        patient.value = patient.value.copy(
            address = address
        )
    }

    fun updateEmergencyContact(contact: String) {
        patient.value = patient.value.copy(
            emergencyContact = contact
        )
    }

    fun addMedicalCondition(condition: String) {

        if (
            condition.isNotBlank() &&
            patient.value.medicalHistory.size < 5
        ) {

            val list = patient.value.medicalHistory.toMutableList()

            list.add(condition)

            patient.value = patient.value.copy(
                medicalHistory = list
            )

        }

    }

    fun removeMedicalCondition(condition: String) {

        val list = patient.value.medicalHistory.toMutableList()

        list.remove(condition)

        patient.value = patient.value.copy(
            medicalHistory = list
        )

    }

}