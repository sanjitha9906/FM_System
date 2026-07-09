package com.example.fmsystem.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fmsystem.models.MonitoringData
import com.example.fmsystem.websocket.WebSocketManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonitoringViewModel : ViewModel() {

    private val _data = MutableStateFlow(MonitoringData())
    val data: StateFlow<MonitoringData> = _data

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    private val webSocketManager = WebSocketManager(
        onDataReceived = { newData ->
            _data.value = newData
        },
        onConnectionChanged = { connected ->
            _isConnected.value = connected
        }
    )

    fun connectWebSocket() {
        webSocketManager.connect()
    }

    fun disconnectWebSocket() {
        webSocketManager.disconnect()
    }

    fun getHealthStatus(data: MonitoringData): String {
        return when {
            data.fetalHeartRate == 0 &&
                    data.motherHeartRate == 0 &&
                    data.headCircumference == 0f ->
                "Waiting for Jetson Nano data"

            data.fetalHeartRate !in 110..160 ->
                "Abnormal Fetal Heart Rate"

            data.motherHeartRate !in 60..100 ->
                "Abnormal Maternal Heart Rate"

            data.movementCount < 3 ->
                "Low Fetal Movement"

            else -> "Normal"
        }
    }

    override fun onCleared() {
        super.onCleared()
        webSocketManager.disconnect()
    }
}