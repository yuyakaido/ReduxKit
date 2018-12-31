package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.AppState

object AppReducer : ReducerType<AppState, AppAction> {

    override fun reduce(state: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.SessionAction -> {
                state.copy(
                    session = SessionReducer.reduce(state.session, action)
                )
            }
        }
    }

}