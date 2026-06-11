package com.example.fmsystem.repository

import com.example.fmsystem.api.ApiClient
import com.example.fmsystem.models.User

class AuthRepository {

    suspend fun login(email: String, password: String): Boolean {
        val response = ApiClient.apiService.login(
            User(name = "", email = email, password = password)
        )
        return response.isSuccessful
    }

    suspend fun signup(name: String, email: String, password: String): Boolean {
        val response = ApiClient.apiService.signup(
            User(name = name, email = email, password = password)
        )
        return response.isSuccessful
    }
}