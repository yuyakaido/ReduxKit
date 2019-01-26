package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType
import com.yuyakaido.android.reduxkit.sample.app.state.store.SearchStoreState
import com.yuyakaido.android.reduxkit.sample.app.state.store.StarStoreState
import com.yuyakaido.android.reduxkit.sample.app.state.view.SearchViewState
import com.yuyakaido.android.reduxkit.sample.app.state.view.StarViewState

data class AppState(
    val domain: DomainState = DomainState(),
    val search: SearchStoreState = SearchStoreState(),
    val star: StarStoreState = StarStoreState()
) : StateType {

    fun toSearchViewState(): SearchViewState {
        return SearchViewState(
            isLoading = search.isLoading,
            repos = search.repos.map { domain.findRepoById(it.id) }
        )
    }

    fun toStarViewState(): StarViewState {
        return StarViewState(
            isLoading = star.isLoading,
            repos = star.repos.map { domain.findRepoById(it.id) }
        )
    }

}