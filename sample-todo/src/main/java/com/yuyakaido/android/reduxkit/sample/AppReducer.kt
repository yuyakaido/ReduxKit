package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ReducerType

object AppReducer : ReducerType<AppState, AppAction> {

    override fun reduce(state: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.AddTodo -> {
                state.copy(todos = state.todos.plus(action.todo))
            }
            is AppAction.CompleteTodo -> {
                state.copy(todos = state.todos.minus(action.todo))
            }
        }
    }

}