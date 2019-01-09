package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType

data class PresentationState(
    val searchedRepos: List<SearchedRepo> = emptyList(),
    val starredRepos: List<StarredRepo> = emptyList()
) : StateType {

    data class SearchedRepo(
        val id: Long,
        val isStarred: Boolean
    )

    data class StarredRepo(
        val id: Long,
        val isStarred: Boolean
    )

}