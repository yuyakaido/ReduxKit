package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType

data class AppState(
    val session: SessionState = SessionState()
) : StateType