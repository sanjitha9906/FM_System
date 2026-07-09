package com.example.fmsystem.models

data class MonitoringData(
    val fetalHeartRate: Int = 0,
    val motherHeartRate: Int = 0,
    val headCircumference: Float = 0f,
    val movementCount: Int = 0
)