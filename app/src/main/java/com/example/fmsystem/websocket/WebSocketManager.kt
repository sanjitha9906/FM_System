package com.example.fmsystem.websocket

import com.example.fmsystem.models.MonitoringData
import com.example.fmsystem.utils.Constants
import okhttp3.*
import org.json.JSONObject

class WebSocketManager(
    private val onDataReceived: (MonitoringData) -> Unit,
    private val onConnectionChanged: (Boolean) -> Unit
) {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request.Builder()
            .url(Constants.WS_URL)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                onConnectionChanged(true)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val json = JSONObject(text)

                    val data = MonitoringData(
                        fetalHeartRate = json.optInt("fetalHeartRate", 0),
                        motherHeartRate = json.optInt("motherHeartRate", 0),
                        headCircumference = json.optDouble("headCircumference", 0.0).toFloat(),
                        movementCount = json.optInt("movementCount", 0)
                    )

                    onDataReceived(data)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                webSocket: WebSocket,
                t: Throwable,
                response: Response?
            ) {
                onConnectionChanged(false)
                t.printStackTrace()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                onConnectionChanged(false)
            }
        })
    }

    fun disconnect() {
        webSocket?.close(1000, "Closed")
        webSocket = null
        onConnectionChanged(false)
    }
}