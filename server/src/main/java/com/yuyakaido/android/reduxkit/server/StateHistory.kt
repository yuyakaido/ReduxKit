package com.yuyakaido.android.reduxkit.server

import com.yuyakaido.android.reduxkit.core.StateType

data class StateHistory(
  val action: String,
  val state: StateType
)