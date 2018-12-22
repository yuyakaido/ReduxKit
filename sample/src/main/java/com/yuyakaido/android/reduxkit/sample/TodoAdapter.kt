package com.yuyakaido.android.reduxkit.sample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TodoAdapter(
    private var todos: List<Todo> = emptyList()
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(inflate.inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.title.text = todo.title
        holder.date.text = todo.date.toString()
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val date: TextView = view.findViewById(R.id.date)
    }

}