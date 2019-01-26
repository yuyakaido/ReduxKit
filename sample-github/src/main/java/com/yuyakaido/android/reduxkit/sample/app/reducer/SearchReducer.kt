package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.store.SearchStoreState

object SearchReducer : ReducerType<SearchStoreState, AppAction.SearchAction> {

    override fun reduce(state: SearchStoreState, action: AppAction.SearchAction): SearchStoreState {
        return when (action) {
            is AppAction.SearchAction.RefreshLoading -> {
                state.copy(
                    isLoading = action.isLoading
                )
            }
            is AppAction.SearchAction.RefreshRepos -> {
                state.copy(
                    repos = action.repos.map { SearchStoreState.SearchRepo(it.id) }
                )
            }
        }
    }

}