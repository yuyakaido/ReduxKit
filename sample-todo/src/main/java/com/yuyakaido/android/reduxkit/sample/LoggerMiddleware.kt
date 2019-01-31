package com.yuyakaido.android.reduxkit.sample

import android.util.Log
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import io.reactivex.Single

class LoggerMiddleware : MiddlewareType<AppState, AppAction> {

  override fun before(state: AppState, action: AppAction): Single<AppAction> {
    Log.d("ReduxKit", "Before dispatching: ${action::class.java.simpleName}")
    return Single.just(action)
  }

  override fun after(state: AppState, action: AppAction): Single<AppAction> {
    Log.d("ReduxKit", "After dispatching: ${action::class.java.simpleName}")
    return Single.just(action)
  }

}