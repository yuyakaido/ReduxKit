package com.yuyakaido.android.reduxkit.middleware.thunk

import com.yuyakaido.android.reduxkit.core.ActionType
import io.reactivex.Single

interface AsyncActionType : ActionType {
  fun execute(dispatcher: Dispatcher): Single<ActionType>
}