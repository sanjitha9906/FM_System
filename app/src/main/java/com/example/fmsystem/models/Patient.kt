package com.example.fmsystem.models

data class Patient(

    val name: String = "",

    val age: String = "",

    val phone: String = "",

    val address: String = "",

    val pregnancyWeek: String = "",

    val bloodGroup: String = "",

    val doctorName: String = "",

    val emergencyContact: String = "",

    val medicalHistory: MutableList<String> = mutableListOf()

)