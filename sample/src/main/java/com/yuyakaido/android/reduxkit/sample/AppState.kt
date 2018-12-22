package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.StateType

data class AppState(
    val todos: List<Todo> = Todo.createTodos()
) : StateType