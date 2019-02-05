package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.store.StarStoreState

object StarReducer : ReducerType<StarStoreState, AppAction.StarAction> {

  override fun reduce(state: StarStoreState, action: AppAction.StarAction): StarStoreState {
    return when (action) {
      is AppAction.StarAction.RefreshLoading -> {
        state.copy(isLoading = action.isLoading)
      }
      is AppAction.StarAction.RefreshRepos -> {
        state.copy(repos = action.repos.map { StarStoreState.StarRepo(it.id) })
      }
      is AppAction.StarAction.AddRepo -> {
        state.copy(repos = listOf(StarStoreState.StarRepo(action.repo.id)).plus(state.repos))
      }
      is AppAction.StarAction.RemoveRepo -> {
        state.copy(repos = state.repos.minus(StarStoreState.StarRepo(action.repo.id)))
      }
    }
  }

}