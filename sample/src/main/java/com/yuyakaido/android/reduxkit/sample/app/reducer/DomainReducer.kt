package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.DomainState

object DomainReducer : ReducerType<DomainState, AppAction.SessionAction.DomainAction> {

    override fun reduce(state: DomainState, action: AppAction.SessionAction.DomainAction): DomainState {
        return when (action) {
            is AppAction.SessionAction.DomainAction.StarRepo -> {
                state.apply {
                    state.repos[action.repo.id] = action.repo.copy(isStarred = true)
                }
            }
            is AppAction.SessionAction.DomainAction.UnstarRepo -> {
                state.apply {
                    state.repos[action.repo.id] = action.repo.copy(isStarred = false)
                }
            }
        }
    }

}