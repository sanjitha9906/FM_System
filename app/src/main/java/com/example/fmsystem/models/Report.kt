package com.example.fmsystem.models

data class Report(
    val id: Int,
    val date: String,
    val fetalHeartRate: Int,
    val motherHeartRate: Int,
    val headCircumference: Float,
    val movementCount: Int,
    val status: String
)