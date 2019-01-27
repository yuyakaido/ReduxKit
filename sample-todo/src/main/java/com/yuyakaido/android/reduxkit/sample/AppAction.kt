package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType

sealed class AppAction : ActionType {
  data class AddTodo(val todo: Todo) : AppAction()
  data class CompleteTodo(val todo: Todo) : AppAction()
}