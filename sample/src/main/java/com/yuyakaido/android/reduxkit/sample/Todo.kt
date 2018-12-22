package com.yuyakaido.android.reduxkit.sample

import java.util.*

data class Todo(
    val title: String,
    val date: Date,
    val isCompleted: Boolean
) {

    companion object {
        fun createTodos(): List<Todo> {
            return List(10) { index ->
                Todo(
                    title = index.toString(),
                    date = Date(),
                    isCompleted = false
                )
            }
        }

        fun new(): Todo {
            return Todo(
                title = "New!",
                date = Date(),
                isCompleted = false
            )
        }
    }

}