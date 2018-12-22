package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ReducerType

class AppReducer : ReducerType<AppState, AppAction> {

    override fun reduce(state: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.AddTodo -> {
                state.copy(todos = state.todos.plus(action.todo))
            }
            is AppAction.RemoveTodo -> {
                state.copy(todos = state.todos.minus(action.todo))
            }
            is AppAction.CompleteTodo -> {
                state.copy(todos = state.todos.map { if (it == action.todo) { action.todo } else { it } })
            }
        }
    }

}