package com.yuyakaido.android.reduxkit.sample.app.state.store

import com.yuyakaido.android.reduxkit.core.StateType

data class SearchStoreState(
    val isLoading: Boolean = false,
    val repos: List<SearchRepo> = emptyList()
) : StateType {

    data class SearchRepo(
        val id: Long
    )

}