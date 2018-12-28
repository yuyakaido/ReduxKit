package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ReducerType

class AppReducer : ReducerType<AppState, AppAction> {

    override fun reduce(state: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.ReplaceUser -> {
                state.copy(user = state.user.copy(value = action.user))
            }
            is AppAction.ReplaceRepo -> {
                state.copy(repos = action.repos)
            }
        }
    }

}