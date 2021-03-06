package com.yuyakaido.android.reduxkit.middleware.logger

import android.util.Log
import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Single

class LoggerMiddleware : MiddlewareType {

  override fun before(state: StateType, action: ActionType): Single<ActionType> {
    Log.d("ReduxKit", "Before dispatching: ${action::class.java.simpleName}")
    return Single.just(action)
  }

  override fun after(state: StateType, action: ActionType): Single<ActionType> {
    Log.d("ReduxKit", "After dispatching: ${action::class.java.simpleName}")
    return Single.just(action)
  }

}