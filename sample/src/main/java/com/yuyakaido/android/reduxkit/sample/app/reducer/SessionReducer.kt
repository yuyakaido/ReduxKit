package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.SessionState

object SessionReducer : ReducerType<SessionState, AppAction.SessionAction> {

    override fun reduce(state: SessionState, action: AppAction.SessionAction): SessionState {
        return when (action) {
            is AppAction.SessionAction.ReplaceUser -> {
                state.copy(user = state.user.copy(value = action.user))
            }
            is AppAction.SessionAction.ReplaceOwnRepos -> {
                state.copy(ownRepos = action.repos)
            }
            is AppAction.SessionAction.ReplaceSearchedRepos -> {
                state.copy(
                    searchedRepos = state.searchedRepos.apply { put(action.query, action.repos) }
                )
            }
        }
    }

}