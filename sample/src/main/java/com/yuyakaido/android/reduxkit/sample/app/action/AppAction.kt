package com.yuyakaido.android.reduxkit.sample.app.action

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo

sealed class AppAction : ActionType {
    sealed class SessionAction : AppAction() {
        data class ReplaceUser(val user: Owner) : SessionAction()
        data class ReplaceOwnRepos(val repos: List<Repo>) : SessionAction()
        data class ReplaceSearchedRepos(val query: String, val repos: List<Repo>) : SessionAction()
    }
}