package com.yuyakaido.android.reduxkit.sample.presentation

import com.yuyakaido.android.reduxkit.sample.app.ReduxKit
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

  protected val appStore: AppStore
    get() = (requireContext().applicationContext as ReduxKit).appStore

}