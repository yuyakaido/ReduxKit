package com.yuyakaido.android.reduxkit.sample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class AppStore(
  private val initialState: AppState = AppState()
) : StoreType<AppState, AppAction> {

  private val state = BehaviorRelay.createDefault(initialState)
  private val middlewares = mutableListOf<MiddlewareType<AppState, AppAction>>()

  override fun dispatch(action: AppAction) {
    state.value?.let { currentState ->
      middlewares.forEach { middleware -> middleware.before(currentState, action) }
      val nextState = AppReducer.reduce(currentState, action)
      state.accept(nextState)
      middlewares.forEach { middleware -> middleware.after(nextState, action) }
    }
  }

  override fun observable(): Observable<AppState> {
    return state.observeOn(AndroidSchedulers.mainThread())
  }

}