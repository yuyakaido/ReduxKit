package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.sample.domain.StarredRepo

data class StarredReposState(
    val repos: List<StarredRepo>
)