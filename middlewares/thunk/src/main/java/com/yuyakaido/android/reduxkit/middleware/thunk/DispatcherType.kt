package com.yuyakaido.android.reduxkit.middleware.thunk

import com.yuyakaido.android.reduxkit.core.ActionType

interface DispatcherType {
  fun dispatch(action: ActionType)
}