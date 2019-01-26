package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.StateType
import io.reactivex.Observable

interface StoreType<STATE : StateType, ACTION : ActionType> {
    fun dispatch(action: ACTION)
    fun observable(): Observable<STATE>
}