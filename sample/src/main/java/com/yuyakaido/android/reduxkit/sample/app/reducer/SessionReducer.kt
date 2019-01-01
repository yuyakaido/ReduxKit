package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.SessionState
import com.yuyakaido.android.reduxkit.sample.misc.Pack

object SessionReducer : ReducerType<SessionState, AppAction.SessionAction> {

    override fun reduce(state: SessionState, action: AppAction.SessionAction): SessionState {
        return when (action) {
            is AppAction.SessionAction.ReplaceSearchedRepos -> {
                state.copy(
                    domain = state.domain.copy(
                        repos = state.domain.repos.apply { putAll(action.repos.associateBy { it.id }) }
                    ),
                    presentation = state.presentation.copy(
                        searchedRepos = action.repos.map { it.id }
                    )
                )
            }
            is AppAction.SessionAction.ReplaceStarredRepos -> {
                state.copy(
                    domain = state.domain.copy(
                        repos = state.domain.repos.apply { putAll(action.repos.associateBy { it.id }) }
                    ),
                    presentation = state.presentation.copy(
                        starredRepos = action.repos.map { it.id }
                    )
                )
            }
            is AppAction.SessionAction.ReplaceUser -> {
                state.copy(
                    domain = state.domain.copy(user = Pack(action.user))
                )
            }
        }
    }

}