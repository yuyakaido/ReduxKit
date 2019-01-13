package com.yuyakaido.android.reduxkit.sample.app.store

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.reducer.AppReducer
import com.yuyakaido.android.reduxkit.sample.app.state.AppState
import com.yuyakaido.android.reduxkit.server.StateProvider
import io.reactivex.Observable

class AppStore(
    private val initial: AppState = AppState()
) : StoreType<AppState, AppAction>, StateProvider {

    private val state = BehaviorRelay.createDefault(initial)
    private val middlewares = mutableListOf<MiddlewareType<AppState, AppAction>>()

    override fun dispatch(action: AppAction) {
        state.value?.let { current ->
            middlewares.forEach { middleware -> middleware.before(current, action) }
            val next = AppReducer.reduce(current, action)
            state.accept(next)
            middlewares.forEach { middleware -> middleware.after(next, action) }
        }
    }

    override fun observable(): Observable<AppState> {
        return state
    }

    override fun addMiddleware(middleware: MiddlewareType<AppState, AppAction>) {
        middlewares.add(middleware)
    }

    override fun removeMiddleware(middleware: MiddlewareType<AppState, AppAction>) {
        middlewares.remove(middleware)
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