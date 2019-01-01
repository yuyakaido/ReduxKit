package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class SearchRepoState(
    val repos: List<Repo>
)