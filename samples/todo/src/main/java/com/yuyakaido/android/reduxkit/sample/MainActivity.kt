package com.yuyakaido.android.reduxkit.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.yuyakaido.android.reduxkit.sample.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setupRecyclerView()
    setupFloatingActionButton()
    refresh()
  }

  override fun onDestroy() {
    disposables.dispose()
    super.onDestroy()
  }

  private fun setupRecyclerView() {
    val adapter = TodoAdapter()
    binding.recyclerView.layoutManager = LinearLayoutManager(this)
    binding.recyclerView.adapter = adapter
    getAppStore().observable()
      .subscribeBy { state ->
        adapter.setTodos(state.todos)
        adapter.notifyDataSetChanged()
      }
      .addTo(disposables)
  }

  private fun setupFloatingActionButton() {
    binding.floatingActionButton
      .setOnClickListener {
        val todo = Todo("🎉 NewTask!")
        val action = AppAction.AddTodo(todo)
        getAppStore().dispatch(action)
      }
  }

  private fun getAppStore(): AppStore {
    return (application as ReduxKit).appStore
  }

  private fun refresh() {
    getAppStore().dispatch(AppAction.RefreshTodos(Todo.createSampleTodos()))
  }

}
