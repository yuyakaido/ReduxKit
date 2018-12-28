package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.StateType
import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class AppState(
    val repos: List<Repo> = emptyList()
) : StateType