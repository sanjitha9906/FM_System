package com.example.fmsystem.api

import com.example.fmsystem.models.Patient
import com.example.fmsystem.models.Report
import com.example.fmsystem.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body user: User): Response<User>

    @POST("auth/signup")
    suspend fun signup(@Body user: User): Response<User>

    @POST("patient/create")
    suspend fun savePatient(@Body patient: Patient): Response<Patient>

    @GET("reports")
    suspend fun getReports(): Response<List<Report>>
}