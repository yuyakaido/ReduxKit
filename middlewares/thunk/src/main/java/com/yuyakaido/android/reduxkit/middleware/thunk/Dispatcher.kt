package com.yuyakaido.android.reduxkit.middleware.thunk

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.StoreType

class Dispatcher(
  private val store: StoreType<*>
) {

  fun dispatch(action: ActionType) {
    store.dispatch(action)
  }

}