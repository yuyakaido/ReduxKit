package com.yuyakaido.android.reduxkit.middleware.logger

import android.util.Log
import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Single

class LoggerMiddleware<STATE : StateType, ACTION : ActionType> : MiddlewareType<STATE, ACTION> {

  override fun before(state: STATE, action: ACTION): Single<ACTION> {
    Log.d("ReduxKit", "Before dispatching: ${action::class.java.simpleName}")
    return Single.just(action)
  }

  override fun after(state: STATE, action: ACTION): Single<ACTION> {
    Log.d("ReduxKit", "After dispatching: ${action::class.java.simpleName}")
    return Single.just(action)
  }

}