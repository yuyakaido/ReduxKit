package com.yuyakaido.android.reduxkit.sample.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.yuyakaido.android.reduxkit.sample.AppStore

class ReduxKit : Application() {

    val store = AppStore()

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
    }

    private fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
    }

}