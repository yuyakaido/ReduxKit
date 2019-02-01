package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface StoreType<STATE : StateType> {
  fun dispatch(action: ActionType): Disposable
  fun observable(): Observable<out STATE>
  fun addMiddleware(middleware: MiddlewareType)
  fun removeMiddleware(middleware: MiddlewareType)
}