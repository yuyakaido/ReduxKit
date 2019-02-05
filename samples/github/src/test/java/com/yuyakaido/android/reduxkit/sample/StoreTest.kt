package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.AppState
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import io.reactivex.observers.TestObserver
import org.junit.Test

class StoreTest {

  @Test
  fun dispatchTest() {
    val state = AppState()
    val store = AppStore(state)
    val observer = TestObserver<AppState>()
    store.observable().subscribe(observer)

    observer.assertValueCount(1)
    observer.assertValueAt(0, state)

    val action = AppAction.SearchAction.RefreshLoading(true)
    store.dispatch(action)
    observer.assertValueCount(2)
    observer.assertValueAt(1, state.copy(search = state.search.copy(isLoading = true)))
  }

}