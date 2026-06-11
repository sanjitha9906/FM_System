package com.example.fmsystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fmsystem.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginSuccess.value = repository.login(email, password)
        }
    }

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            _loginSuccess.value = repository.signup(name, email, password)
        }
    }
}