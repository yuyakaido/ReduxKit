package com.yuyakaido.android.reduxkit.sample.app.store

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Observable

interface StoreType<STATE : StateType, ACTION : ActionType> {
    fun dispatch(action: ACTION)
    fun observable(): Observable<STATE>
    fun addMiddleware(middleware: MiddlewareType<STATE, ACTION>)
    fun removeMiddleware(middleware: MiddlewareType<STATE, ACTION>)
}