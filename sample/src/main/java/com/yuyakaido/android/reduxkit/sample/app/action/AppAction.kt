package com.yuyakaido.android.reduxkit.sample.app.action

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.sample.domain.*

sealed class AppAction : ActionType {
    sealed class SessionAction : AppAction() {
        sealed class DomainAction : SessionAction() {
            data class StarRepo(val repo: Repo) : DomainAction()
            data class UnstarRepo(val repo: Repo) : DomainAction()
        }
        data class ReplaceSearchedRepos(val query: String, val repos: List<Repo>) : SessionAction()
        data class ReplaceStarredRepos(val repos: List<Repo>) : SessionAction()
        data class ReplaceUser(val user: Owner) : SessionAction()
    }
}