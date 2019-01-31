package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.MiddlewareType
import io.reactivex.Single

class ThunkMiddleware : MiddlewareType<AppState, AppAction> {

  override fun before(state: AppState, action: AppAction): Single<AppAction> {
    return if (action is AsyncActionType) {
      action.execute()
    } else {
      Single.just(action)
    }
  }

  override fun after(state: AppState, action: AppAction): Single<AppAction> {
    return Single.just(action)
  }

}