package com.yuyakaido.android.reduxkit.sample

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.server.StateProvider
import io.reactivex.Observable

class AppStore(
    private val initial: AppState = AppState(),
    private val reducer: AppReducer = AppReducer()
) : StoreType<AppState, AppAction>, StateProvider {

    private val state = BehaviorRelay.createDefault(initial)

    override fun dispatch(action: AppAction) {
        state.value?.let { current ->
            state.accept(reducer.reduce(current, action))
        }
    }

    override fun observable(): Observable<AppState> {
        return state
    }

    override fun json(): String {
        val current = state.value
        return if (current == null) {
            JsonObject().toString()
        } else {
            Gson().toJson(current)
        }
    }

}