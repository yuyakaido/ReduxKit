package com.yuyakaido.android.reduxkit.sample

import java.util.*

data class Todo(
    val title: String,
    val date: Date,
    val isCompleted: Boolean
) {

    companion object {
        fun createTodos(): List<Todo> {
            return listOf(
                Todo(title = "📝 AdventCalendarを書く", date = Date(), isCompleted = false),
                Todo(title = "🚄 新幹線のチケットを取る", date = Date(), isCompleted = false),
                Todo(title = "🏠 マンションの更新をする", date = Date(), isCompleted = true)
            )
        }

        fun new(): Todo {
            return Todo(
                title = "🎉 NewTask",
                date = Date(),
                isCompleted = false
            )
        }
    }

}