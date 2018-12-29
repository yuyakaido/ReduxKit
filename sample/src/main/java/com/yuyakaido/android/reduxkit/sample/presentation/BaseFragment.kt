package com.yuyakaido.android.reduxkit.sample.presentation

import com.yuyakaido.android.reduxkit.sample.AppStore
import com.yuyakaido.android.reduxkit.sample.app.ReduxKit
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    protected val appStore: AppStore
        get() = (requireContext().applicationContext as ReduxKit).appStore

}