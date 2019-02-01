package com.yuyakaido.android.reduxkit.middleware.thunk

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Single

class ThunkMiddleware(
  private val dispatcher: DispatcherType
) : MiddlewareType {

  override fun before(state: StateType, action: ActionType): Single<ActionType> {
    return if (action is AsyncActionType) {
      action.execute(dispatcher)
    } else {
      Single.just(action)
    }
  }

  override fun after(state: StateType, action: ActionType): Single<ActionType> {
    return Single.just(action)
  }

}