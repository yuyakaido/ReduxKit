package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType
import com.yuyakaido.android.reduxkit.sample.domain.SearchedRepo
import com.yuyakaido.android.reduxkit.sample.domain.StarredRepo

data class SessionState(
    val domain: DomainState = DomainState(),
    val presentation: PresentationState = PresentationState()
) : StateType {

    fun toSearchedReposState(): SearchedReposState {
        return SearchedReposState(
            repos = presentation.searchedRepos.map {
                SearchedRepo(domain.findRepoById(it.id), it.isStarred)
            }
        )
    }

    fun toStarredReposState(): StarredReposState {
        return StarredReposState(
            repos = presentation.starredRepos.map {
                StarredRepo(domain.findRepoById(it.id), it.isStarred)
            }
        )
    }

}