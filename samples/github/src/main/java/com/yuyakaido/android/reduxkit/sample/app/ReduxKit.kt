package com.yuyakaido.android.reduxkit.sample.app

import com.facebook.stetho.Stetho
import com.yuyakaido.android.reduxkit.middleware.logger.LoggerMiddleware
import com.yuyakaido.android.reduxkit.middleware.thunk.Dispatcher
import com.yuyakaido.android.reduxkit.middleware.thunk.ThunkMiddleware
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
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
    initializeAppStore()
  }

  private fun initializeStetho() {
    Stetho.initializeWithDefaults(this)
  }

  private fun initializeAppStore() {
    appStore.addMiddleware(ThunkMiddleware(Dispatcher(appStore)))
    appStore.addMiddleware(LoggerMiddleware())
  }

}