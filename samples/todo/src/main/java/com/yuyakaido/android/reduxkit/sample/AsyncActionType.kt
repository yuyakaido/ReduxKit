package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import io.reactivex.Single

interface AsyncActionType : ActionType {
  fun execute(): Single<AppAction>
}