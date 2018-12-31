package com.yuyakaido.android.reduxkit.sample.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.yuyakaido.android.reduxkit.sample.BuildConfig
import com.yuyakaido.android.reduxkit.sample.databinding.ActivityLaunchAuthorizeBinding

class LaunchAuthorizeActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LaunchAuthorizeActivity::class.java)
        }
    }

    private val binding by lazy { ActivityLaunchAuthorizeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButton()
    }

    private fun setupButton() {
        binding.authorize.setOnClickListener { authorize() }
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