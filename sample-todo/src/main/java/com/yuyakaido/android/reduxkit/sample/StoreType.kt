package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface StoreType<STATE : StateType, ACTION : ActionType> {
  fun dispatch(action: ACTION): Disposable
  fun observable(): Observable<STATE>
  fun addMiddleware(middleware: MiddlewareType<STATE, ACTION>)
  fun removeMiddleware(middleware: MiddlewareType<STATE, ACTION>)
}