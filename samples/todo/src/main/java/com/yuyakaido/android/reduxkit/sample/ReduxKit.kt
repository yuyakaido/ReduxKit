package com.yuyakaido.android.reduxkit.sample

import android.app.Application
import com.yuyakaido.android.reduxkit.middleware.logger.LoggerMiddleware

class ReduxKit : Application() {

  val appStore = AppStore()

  override fun onCreate() {
    super.onCreate()
    appStore.addMiddleware(LoggerMiddleware())
  }

}