package com.yuyakaido.android.reduxkit.sample

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yuyakaido.android.reduxkit.server.ReduxHttpServer
import com.yuyakaido.android.reduxkit.server.ReduxWekSocketServer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : AppCompatActivity() {

    private val httpServer by lazy { ReduxHttpServer(this) }
    private val webSocketServer = ReduxWekSocketServer()
    private val webSocketClient = ReduxWebSocketClient()

    private val disposables = CompositeDisposable()
    private val store = AppStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNetworking()
        setupRecyclerView()
        setupFloatingActionButton()
    }

    override fun onDestroy() {
        httpServer.stop()
        webSocketServer.stop()
        webSocketClient.close()
        disposables.dispose()
        super.onDestroy()
    }

    private fun setupNetworking() {
        httpServer.start()
        webSocketServer.isReuseAddr = true
        webSocketServer.start()
        Handler().post {
            webSocketClient.connect()
            Handler().post { webSocketClient.send("Hello!") }
        }
    }

    private fun setupRecyclerView() {
        val adapter = TodoAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        store.observable()
            .map { state -> state.todos }
            .map { todos -> todos.filterNot { todo -> todo.isCompleted } }
            .subscribeBy { todos ->
                adapter.setTodos(todos)
                adapter.notifyDataSetChanged()
            }
            .addTo(disposables)
    }

    private fun setupFloatingActionButton() {
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floating_action_button)
        floatingActionButton.setOnClickListener {
            store.dispatch(AppAction.AddTodo(Todo.new()))
        }
    }

}
