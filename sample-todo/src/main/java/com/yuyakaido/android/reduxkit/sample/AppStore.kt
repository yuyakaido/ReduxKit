package com.yuyakaido.android.reduxkit.sample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.yuyakaido.android.reduxkit.core.MiddlewareType
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class AppStore(
  private val initialState: AppState = AppState()
) : StoreType<AppState, AppAction> {

  private val state = BehaviorRelay.createDefault(initialState)
  private val middlewares = mutableListOf<MiddlewareType<AppState, AppAction>>()

  override fun dispatch(action: AppAction): Disposable {
    return state.value?.let { currentState ->
      Single.just(action)
        .flatMap { originalAction ->
          var stream = Single.just(originalAction)
          middlewares.forEach { middleware ->
            stream = stream.flatMap { currentAction -> middleware.before(currentState, currentAction) }
          }
          return@flatMap stream
        }
        .doOnSuccess { update(it) }
        .flatMap { originalAction ->
          var stream = Single.just(originalAction)
          middlewares.forEach { middleware ->
            stream = stream.flatMap { currentAction -> middleware.after(currentState, currentAction) }
          }
          return@flatMap stream
        }
        .subscribe()
    } ?: Disposables.disposed()
  }

  private fun update(action: AppAction) {
    state.value?.let { currentState ->
      val nextState = AppReducer.reduce(currentState, action)
      state.accept(nextState)
    }
  }

  override fun observable(): Observable<AppState> {
    return state.observeOn(AndroidSchedulers.mainThread())
  }

  override fun addMiddleware(middleware: MiddlewareType<AppState, AppAction>) {
    middlewares.add(middleware)
  }

  override fun removeMiddleware(middleware: MiddlewareType<AppState, AppAction>) {
    middlewares.remove(middleware)
  }

}