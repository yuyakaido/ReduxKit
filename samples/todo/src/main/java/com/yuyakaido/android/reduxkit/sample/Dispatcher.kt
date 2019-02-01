package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.DispatcherType

class Dispatcher(
  private val store: AppStore
) : DispatcherType {

  override fun dispatch(action: ActionType) {
    store.dispatch(action)
  }

}