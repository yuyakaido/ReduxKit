package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class StarredReposState(
    val repos: List<Repo>
)