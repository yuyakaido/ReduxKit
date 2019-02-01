package com.yuyakaido.android.reduxkit.core

import io.reactivex.Observable
import io.reactivex.Single

interface StateType

interface ActionType

interface ReducerType<STATE : StateType, ACTION : ActionType> {
  fun reduce(state: STATE, action: ACTION): STATE
}

interface StoreType<STATE : StateType> {
  fun dispatch(action: ActionType)
  fun observable(): Observable<out STATE>
  fun addMiddleware(middleware: MiddlewareType)
  fun removeMiddleware(middleware: MiddlewareType)
}

interface MiddlewareType {
  fun before(state: StateType, action: ActionType): Single<ActionType>
  fun after(state: StateType, action: ActionType): Single<ActionType>
}
