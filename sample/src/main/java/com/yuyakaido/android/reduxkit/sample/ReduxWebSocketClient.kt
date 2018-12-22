package com.yuyakaido.android.reduxkit.sample

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class ReduxWebSocketClient : WebSocketClient(URI("ws://192.168.0.2:8081")) {

    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.v("WebSocketClient", "OnOpen: ${handshakedata?.httpStatus} ${handshakedata?.httpStatusMessage}")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.v("WebSocketClient", "OnClose: $code $reason")
    }

    override fun onMessage(message: String?) {
        Log.v("WebSocketClient", "OnMessage: $message")
    }

    override fun onError(ex: Exception?) {
        Log.v("WebSocketClient", "OnError: ${ex?.message}")
    }

}