package com.yuyakaido.android.reduxkit.server

import android.content.Context
import fi.iki.elonen.NanoHTTPD

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Server(
    private val context: Context
) : NanoHTTPD(8080) {

    override fun serve(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
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

}
