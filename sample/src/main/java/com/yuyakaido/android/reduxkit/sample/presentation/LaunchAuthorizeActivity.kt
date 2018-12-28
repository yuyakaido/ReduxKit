package com.yuyakaido.android.reduxkit.sample.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yuyakaido.android.reduxkit.sample.BuildConfig
import com.yuyakaido.android.reduxkit.sample.R

class LaunchAuthorizeActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LaunchAuthorizeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_authorize)
        setupButton()
    }

    private fun setupButton() {
        findViewById<View>(R.id.authorize)
            .setOnClickListener { authorize() }
    }

    private fun authorize() {
        val uri = Uri.parse("https://github.com/login/oauth/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
            .build()
        startActivity(Intent(Intent.ACTION_VIEW, uri))
        finish()
    }

}