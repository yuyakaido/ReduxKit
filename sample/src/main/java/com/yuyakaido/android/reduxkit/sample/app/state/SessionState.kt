package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType

data class SessionState(
    val domain: DomainState = DomainState(),
    val presentation: PresentationState = PresentationState()
) : StateType {

    fun toSearchedReposState(): SearchedReposState {
        return SearchedReposState(
            repos = presentation.searchedRepos.map { domain.findRepoById(it.id) }
        )
    }

    fun toStarredReposState(): StarredReposState {
        return StarredReposState(
            repos = presentation.starredRepos.map { domain.findRepoById(it.id) }
        )
    }

}