package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.textChangeEvents
import com.yuyakaido.android.reduxkit.sample.app.actioncreator.SearchActionCreator
import com.yuyakaido.android.reduxkit.sample.app.actioncreator.StarActionCreator
import com.yuyakaido.android.reduxkit.sample.databinding.FragmentSearchRepositoryBinding
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchRepositoryFragment : BaseFragment(), RepoAdapter.OnStarClickListener {

  companion object {
    fun newInstance() = SearchRepositoryFragment()
  }

  private lateinit var disposables: CompositeDisposable
  private lateinit var binding: FragmentSearchRepositoryBinding

  @Inject
  lateinit var searchActionCreator: SearchActionCreator

  @Inject
  lateinit var starActionCreator: StarActionCreator

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    disposables = CompositeDisposable()
    binding = FragmentSearchRepositoryBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupEditText()
    setupSwipeRefreshLayout()
    setupRecyclerView()
  }

  override fun onDestroyView() {
    disposables.dispose()
    super.onDestroyView()
  }

  override fun onStarClick(repo: Repo) {
    if (repo.hasStarred) {
      appStore.dispatch(starActionCreator.removeStar(repo))
    } else {
      appStore.dispatch(starActionCreator.addStar(repo))
    }
  }

  private fun setupEditText() {
    binding.editText.textChangeEvents()
      .map { it.text().toString() }
      .distinctUntilChanged()
      .throttleWithTimeout(200, TimeUnit.MILLISECONDS)
      .subscribeBy { query ->
        appStore.dispatch(searchActionCreator.fetchSearchRepositories(query))
      }
      .addTo(disposables)
  }

  private fun setupSwipeRefreshLayout() {
    binding.swipeRefreshLayout.setOnRefreshListener { refresh() }

    appStore.observable()
      .map { it.toSearchViewState() }
      .subscribeBy { binding.swipeRefreshLayout.isRefreshing = it.isLoading }
      .addTo(disposables)
  }

  private fun setupRecyclerView() {
    val adapter = RepoAdapter(listener = this)
    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.recyclerView.adapter = adapter

    appStore.observable()
      .map { it.toSearchViewState() }
      .subscribeBy { state ->
        adapter.setRepos(state.repos)
        adapter.notifyDataSetChanged()
      }
      .addTo(disposables)
  }

  private fun refresh() {
    val query = binding.editText.text.toString()
    appStore.dispatch(searchActionCreator.fetchSearchRepositories(query))
  }

}