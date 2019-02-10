package com.yuyakaido.android.reduxkit.sample.app.store

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StoreType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.reducer.AppReducer
import com.yuyakaido.android.reduxkit.sample.app.state.AppState
import com.yuyakaido.android.reduxkit.server.StateHistory
import com.yuyakaido.android.reduxkit.server.StateProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class AppStore(
  private val initial: AppState = AppState()
) : StoreType<AppState>, StateProvider {

  private val state = BehaviorRelay.createDefault(initial)
  private val middlewares = mutableListOf<MiddlewareType>()

  private val histories = mutableListOf<StateHistory>()

  override fun dispatch(action: ActionType) {
    state.value?.let { currentState ->
      Single.just(action)
        .flatMap { originalAction ->
          var stream = Single.just(originalAction)
          middlewares.forEach { middleware ->
            stream = stream.flatMap { currentAction ->
              middleware.before(currentState, currentAction)
            }
          }
          return@flatMap stream
        }
        .doOnSuccess { update(it) }
        .flatMap { originalAction ->
          var stream = Single.just(originalAction)
          middlewares.forEach { middleware ->
            stream = stream.flatMap { currentAction ->
              middleware.after(currentState, currentAction)
            }
          }
          return@flatMap stream
        }
        .subscribe()
    }
  }

  private fun update(action: ActionType) {
    state.value?.let { currentState ->
      val nextState = AppReducer.reduce(currentState, action as AppAction)
      state.accept(nextState)
      histories.add(StateHistory(action::class.java.simpleName, nextState))
    }
  }

  override fun observable(): Observable<AppState> {
    return state.observeOn(AndroidSchedulers.mainThread())
  }

  override fun addMiddleware(middleware: MiddlewareType) {
    middlewares.add(middleware)
  }

  override fun removeMiddleware(middleware: MiddlewareType) {
    middlewares.remove(middleware)
  }

  override fun json(): String {
    return if (histories.isEmpty()) {
      JsonObject().toString()
    } else {
      Gson().toJson(histories)
    }
  }

}