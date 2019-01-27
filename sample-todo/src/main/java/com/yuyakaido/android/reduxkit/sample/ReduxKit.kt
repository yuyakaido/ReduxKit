package com.yuyakaido.android.reduxkit.sample

import android.app.Application

class ReduxKit : Application() {
  val appStore = AppStore()
}