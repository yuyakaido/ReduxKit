package com.yuyakaido.android.reduxkit.server

import android.content.Context
import android.util.Log
import fi.iki.elonen.NanoHTTPD

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class DevToolServer(
    private val context: Context,
    private val stateProvider: StateProvider
) : NanoHTTPD(8080) {

    enum class RequestType(val path: String) {
        Root("/"),
        Favicon("/favicon.ico"),
        State("/state");

        companion object {
            fun from(uri: String): RequestType {
                return RequestType.values().first { it.path == uri }
            }
        }
    }

    override fun serve(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        val method = session.method
        val uri = session.uri
        Log.v("DevToolServer", "$method $uri")

        val type = RequestType.from(uri)

        return when (type) {
            RequestType.Root,
            RequestType.Favicon -> {
                serveRoot()
            }
            RequestType.State -> {
                serveState()
            }
        }
    }

    private fun serveRoot(): NanoHTTPD.Response {
        val stream: InputStream
        val reader: BufferedReader
        val builder = StringBuilder()
        try {
            stream = context.assets.open("index.html")
            reader = BufferedReader(InputStreamReader(stream))
            var line = reader.readLine()
            while (line != null) {
                builder.append(line)
                builder.append("\n")
                line = reader.readLine()
            }
            reader.close()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return NanoHTTPD.newFixedLengthResponse(builder.toString())
    }

    private fun serveState(): NanoHTTPD.Response {
        return NanoHTTPD.newFixedLengthResponse(stateProvider.json())
    }

}
