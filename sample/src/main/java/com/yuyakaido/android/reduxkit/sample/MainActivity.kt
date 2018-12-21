package com.yuyakaido.android.reduxkit.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuyakaido.android.reduxkit.server.Server

class MainActivity : AppCompatActivity() {

    private val server = Server()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        server.start()
    }

    override fun onDestroy() {
        server.stop()
        super.onDestroy()
    }

}
