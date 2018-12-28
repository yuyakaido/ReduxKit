package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = AccessToken.getOrNull(this)
        if (token == null) {
            startActivity(LaunchAuthorizeActivity.createIntent(this))
        } else {
            startActivity(MainActivity.createIntent(this))
        }
        finish()
    }

}