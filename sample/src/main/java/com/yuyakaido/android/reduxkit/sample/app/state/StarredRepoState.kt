package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class StarredRepoState(
    val repos: List<Repo>
)