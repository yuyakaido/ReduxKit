package com.yuyakaido.android.reduxkit.sample

data class Todo(
  val title: String
) {

  companion object {
    fun createSampleTodos(): List<Todo> {
      return listOf(
        Todo(title = "📝 AdventCalendarを書く"),
        Todo(title = "🚄 新幹線のチケットを取る"),
        Todo(title = "🏠 マンションの更新をする")
      )
    }
  }

}