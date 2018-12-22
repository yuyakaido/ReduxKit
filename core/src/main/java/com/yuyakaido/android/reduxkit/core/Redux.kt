package com.yuyakaido.android.reduxkit.core

interface StateType

interface ActionType

interface ReducerType<STATE : StateType, ACTION : ActionType> {
    fun reduce(state: STATE, action: ACTION): STATE
}