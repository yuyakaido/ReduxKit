package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.sample.domain.Repo

sealed class AppAction : ActionType {
    data class ReplaceRepo(val repos: List<Repo>) : AppAction()
}