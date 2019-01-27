package com.yuyakaido.android.reduxkit.sample.presentation

import com.yuyakaido.android.reduxkit.sample.app.ReduxKit
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

  protected val appStore: AppStore
    get() = (applicationContext as ReduxKit).appStore

}