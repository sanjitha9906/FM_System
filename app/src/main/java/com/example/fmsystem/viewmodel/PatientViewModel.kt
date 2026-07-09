package com.example.fmsystem.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fmsystem.models.Patient
import com.example.fmsystem.repository.PatientRepository

class PatientViewModel : ViewModel() {

    val patient = PatientRepository.patient

    fun savePatient(patientData: Patient) {
        PatientRepository.savePatient(patientData)
    }

    fun updatePhone(phone: String) {
        PatientRepository.updatePhone(phone)
    }

    fun updateAddress(address: String) {
        PatientRepository.updateAddress(address)
    }

    fun updateEmergencyContact(contact: String) {
        PatientRepository.updateEmergencyContact(contact)
    }

    fun addMedicalCondition(condition: String) {
        PatientRepository.addMedicalCondition(condition)
    }

    fun removeMedicalCondition(condition: String) {
        PatientRepository.removeMedicalCondition(condition)
    }

}