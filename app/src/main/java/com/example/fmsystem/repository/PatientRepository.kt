package com.example.fmsystem.repository

import com.example.fmsystem.api.ApiClient
import com.example.fmsystem.models.Patient

class PatientRepository {

    suspend fun savePatient(patient: Patient): Boolean {
        val response = ApiClient.apiService.savePatient(patient)
        return response.isSuccessful
    }
}