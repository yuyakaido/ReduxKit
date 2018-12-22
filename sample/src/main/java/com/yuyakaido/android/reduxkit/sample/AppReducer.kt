package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ReducerType

class AppReducer : ReducerType<AppState, AppAction> {

    override fun reduce(state: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.AddTodo -> {
                state.copy(todos = state.todos.plus(action.todo))
            }
        }
    }

}