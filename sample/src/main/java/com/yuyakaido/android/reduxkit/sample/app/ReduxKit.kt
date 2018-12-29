package com.yuyakaido.android.reduxkit.sample.app

import com.facebook.stetho.Stetho
import com.yuyakaido.android.reduxkit.sample.AppStore
import com.yuyakaido.android.reduxkit.sample.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class ReduxKit : DaggerApplication() {

    @Inject
    lateinit var appStore: AppStore

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
    }

    private fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
    }

}