package com.yuyakaido.android.reduxkit.sample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import com.yuyakaido.android.reduxkit.core.StoreType
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class AppStore(
  private val initialState: AppState = AppState()
) : StoreType<AppState> {

  private val state = BehaviorRelay.createDefault(initialState)
  private val middlewares = mutableListOf<MiddlewareType>()

  override fun dispatch(action: ActionType) {
    state.value?.let { currentState ->
      middlewares.forEach { it.before(currentState, action) }
      val nextState = AppReducer.reduce(currentState, action as AppAction)
      state.accept(nextState)
      middlewares.forEach { it.after(currentState, action) }
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

}