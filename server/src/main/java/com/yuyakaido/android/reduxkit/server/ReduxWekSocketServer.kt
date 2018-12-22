package com.yuyakaido.android.reduxkit.server

import android.util.Log
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

class ReduxWekSocketServer : WebSocketServer(InetSocketAddress("192.168.0.2", 8081)) {

    override fun onStart() {
        Log.v("WebSocketServer", "OnStart:")
    }

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        Log.v("WebSocketServer", "OnOpen: ${conn?.remoteSocketAddress}")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        Log.v("WebSocketServer", "OnClose: ${conn?.remoteSocketAddress}")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        Log.v("WebSocketServer", "OnMessage: $message")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Log.v("WebSocketServer", "OnMessage: ${ex?.message}")
    }

}