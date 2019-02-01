package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.AsyncActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.Dispatcher
import io.reactivex.Single

sealed class AppAction : ActionType {
  data class RefreshTodos(val todos: List<Todo>) : AppAction()
  data class AddTodo(val todo: Todo) : AppAction()
  data class CompleteTodo(val todo: Todo) : AppAction()

  object FetchTodos : AppAction(), AsyncActionType {
    override fun execute(dispatcher: Dispatcher): Single<ActionType> {
      return Single.just(AppAction.RefreshTodos(Todo.createSampleTodos()))
    }
  }
}