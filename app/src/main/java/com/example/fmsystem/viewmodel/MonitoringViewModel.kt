package com.example.fmsystem.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fmsystem.models.MonitoringData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonitoringViewModel : ViewModel() {

    private val _data = MutableStateFlow(MonitoringData())
    val data: StateFlow<MonitoringData> = _data

    fun updateData(newData: MonitoringData) {
        _data.value = newData
    }
}