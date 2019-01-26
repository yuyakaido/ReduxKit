package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.AppState

object AppReducer : ReducerType<AppState, AppAction> {

    override fun reduce(state: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.DomainAction -> {
                state.copy(
                    domain = DomainReducer.reduce(
                        state = state.domain,
                        action = action
                    )
                )
            }
            is AppAction.SearchAction -> {
                state.copy(
                    search = SearchReducer.reduce(
                        state = state.search,
                        action = action
                    )
                )
            }
            is AppAction.StarAction -> {
                state.copy(
                    star = StarReducer.reduce(
                        state = state.star,
                        action = action
                    )
                )
            }
        }
    }

}