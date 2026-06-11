package com.example.fmsystem.websocket

import com.example.fmsystem.models.MonitoringData
import com.example.fmsystem.utils.Constants
import okhttp3.*
import org.json.JSONObject

class WebSocketManager(
    private val onDataReceived: (MonitoringData) -> Unit
) {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request.Builder()
            .url(Constants.WS_URL)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val json = JSONObject(text)

                    val data = MonitoringData(
                        fetalHeartRate = json.optInt("fetalHeartRate", 0),
                        motherHeartRate = json.optInt("motherHeartRate", 0),
                        temperature = json.optDouble("temperature", 0.0).toFloat(),
                        movementCount = json.optInt("movementCount", 0),
                        status = json.optString("status", "Normal")
                    )

                    onDataReceived(data)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    fun disconnect() {
        webSocket?.close(1000, "Closed")
        webSocket = null
    }
}