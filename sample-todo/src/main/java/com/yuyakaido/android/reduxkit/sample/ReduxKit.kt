package com.yuyakaido.android.reduxkit.sample

import android.app.Application

class ReduxKit : Application() {

  val appStore = AppStore()

  override fun onCreate() {
    super.onCreate()
    appStore.addMiddleware(ThunkMiddleware())
    appStore.addMiddleware(LoggerMiddleware())
  }

}