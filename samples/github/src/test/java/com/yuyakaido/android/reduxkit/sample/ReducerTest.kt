package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.reducer.AppReducer
import com.yuyakaido.android.reduxkit.sample.app.state.AppState
import org.junit.Test

class ReducerTest {

  @Test
  fun reduceTest() {
    var state = AppState()
    assert(!state.search.isLoading)

    val action = AppAction.SearchAction.RefreshLoading(isLoading = true)
    state = AppReducer.reduce(state, action)
    assert(state.search.isLoading)
  }

}