package com.yuyakaido.android.reduxkit.sample.app.state.view

import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class SearchViewState(
  val isLoading: Boolean,
  val repos: List<Repo>
)