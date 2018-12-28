package com.yuyakaido.android.reduxkit.sample.app

import android.app.Application
import com.yuyakaido.android.reduxkit.sample.AppStore

class ReduxKit : Application() {

    val store = AppStore()

}