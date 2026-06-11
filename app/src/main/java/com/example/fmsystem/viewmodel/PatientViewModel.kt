package com.example.fmsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fmsystem.models.Patient
import com.example.fmsystem.repository.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientViewModel : ViewModel() {

    private val repository = PatientRepository()

    private val _saved = MutableStateFlow(false)
    val saved: StateFlow<Boolean> = _saved

    fun savePatient(patient: Patient) {
        viewModelScope.launch {
            _saved.value = repository.savePatient(patient)
        }
    }
}