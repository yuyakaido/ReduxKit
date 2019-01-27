package com.yuyakaido.android.reduxkit.sample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class AppStore(
  private val initial: AppState = AppState()
) : StoreType<AppState, AppAction> {

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
    return state.observeOn(AndroidSchedulers.mainThread())
  }

}