package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo

sealed class AppAction : ActionType {
    data class ReplaceUser(val user: Owner) : AppAction()
    data class ReplaceRepo(val repos: List<Repo>) : AppAction()
}