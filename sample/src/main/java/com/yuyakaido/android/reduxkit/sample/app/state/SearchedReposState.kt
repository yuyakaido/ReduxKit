package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.sample.domain.SearchedRepo

data class SearchedReposState(
    val repos: List<SearchedRepo>
)