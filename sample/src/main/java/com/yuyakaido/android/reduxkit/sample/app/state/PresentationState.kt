package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType

data class PresentationState(
    val searchedRepos: List<Long> = emptyList(),
    val starredRepos: List<Long> = emptyList()
) : StateType