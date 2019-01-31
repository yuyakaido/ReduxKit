package com.yuyakaido.android.reduxkit.sample.app.state.store

import com.yuyakaido.android.reduxkit.core.StateType

data class StarStoreState(
  val isLoading: Boolean = false,
  val repos: List<StarRepo> = emptyList()
) : StateType {

  data class StarRepo(
    val id: Long
  )

}