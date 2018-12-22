package com.yuyakaido.android.reduxkit.sample

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class AppStore(
    private val initial: AppState = AppState(),
    private val reducer: AppReducer = AppReducer()
) : StoreType<AppState, AppAction> {

    private val state = BehaviorRelay.createDefault(initial)

    override fun dispatch(action: AppAction) {
        state.value?.let { current ->
            state.accept(reducer.reduce(current, action))
        }
    }

    override fun observable(): Observable<AppState> {
        return state
    }

}