package com.yuyakaido.android.reduxkit.sample.app.action

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.SearchedRepo
import com.yuyakaido.android.reduxkit.sample.domain.StarredRepo

sealed class AppAction : ActionType {
    sealed class SessionAction : AppAction() {
        data class ReplaceSearchedRepos(val query: String, val repos: List<SearchedRepo>) : SessionAction()
        data class ReplaceStarredRepos(val repos: List<StarredRepo>) : SessionAction()
        data class ReplaceUser(val user: Owner) : SessionAction()
    }
}