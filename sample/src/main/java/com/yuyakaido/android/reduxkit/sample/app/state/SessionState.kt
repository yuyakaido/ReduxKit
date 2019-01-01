package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType

data class SessionState(
    val domain: DomainState = DomainState(),
    val presentation: PresentationState = PresentationState()
) : StateType {

    fun toSearchRepoState(): SearchRepoState {
        return SearchRepoState(
            repos = presentation.searchedRepos.map { domain.findRepoById(it) }
        )
    }

    fun toStarredRepoState(): StarredRepoState {
        return StarredRepoState(
            repos = presentation.starredRepos.map { domain.findRepoById(it) }
        )
    }

}