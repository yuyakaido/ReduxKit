package com.yuyakaido.android.reduxkit.core

import io.reactivex.Single

interface StateType

interface ActionType

interface ReducerType<STATE : StateType, ACTION : ActionType> {
  fun reduce(state: STATE, action: ACTION): STATE
}

interface MiddlewareType {
  fun before(state: StateType, action: ActionType): Single<ActionType>
  fun after(state: StateType, action: ActionType): Single<ActionType>
}
