package com.yuyakaido.android.reduxkit.sample.app.middleware

import android.util.Log
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.AppState

class LoggerMiddleware : MiddlewareType<AppState, AppAction> {

    override fun before(state: AppState, action: AppAction) {
        Log.d("ReduxKit", "Before dispatching: ${action::class.java.simpleName}")
    }

    override fun after(state: AppState, action: AppAction) {
        Log.d("ReduxKit", "After dispatching: ${action::class.java.simpleName}")
    }

}