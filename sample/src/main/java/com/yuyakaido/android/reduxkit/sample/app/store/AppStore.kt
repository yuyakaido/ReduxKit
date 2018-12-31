package com.yuyakaido.android.reduxkit.sample.app.store

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.reducer.AppReducer
import com.yuyakaido.android.reduxkit.sample.app.state.AppState
import com.yuyakaido.android.reduxkit.server.StateProvider
import io.reactivex.Observable

class AppStore(
    private val initial: AppState = AppState()
) : StoreType<AppState, AppAction>, StateProvider {

    private val state = BehaviorRelay.createDefault(initial)

    override fun dispatch(action: AppAction) {
        state.value?.let { current ->
            state.accept(AppReducer.reduce(current, action))
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