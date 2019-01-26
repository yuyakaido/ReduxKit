package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.reduxkit.sample.app.actioncreator.StarActionCreator
import com.yuyakaido.android.reduxkit.sample.databinding.FragmentStarRepositoryBinding
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class StarRepositoryFragment : BaseFragment(), RepoAdapter.OnStarClickListener {

    companion object {
        fun newInstance() = StarRepositoryFragment()
    }

    private lateinit var disposables: CompositeDisposable
    private lateinit var binding: FragmentStarRepositoryBinding

    @Inject
    lateinit var starActionCreator: StarActionCreator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        disposables = CompositeDisposable()
        binding = FragmentStarRepositoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeRefreshLayout()
        setupRecyclerView()
        refresh()
    }

    override fun onDestroyView() {
        disposables.dispose()
        super.onDestroyView()
    }

    override fun onStarClick(repo: Repo) {
        if (repo.isStarred) {
            starActionCreator.unstarRepo(repo).addTo(disposables)
        } else {
            starActionCreator.starRepo(repo).addTo(disposables)
        }
    }

    private fun setupRecyclerView() {
        val adapter = RepoAdapter(listener = this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        appStore.observable()
            .map { it.toStarViewState() }
            .subscribeBy { state ->
                adapter.setRepos(state.repos)
                adapter.notifyDataSetChanged()
            }
            .addTo(disposables)
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener { refresh() }

        appStore.observable()
            .map { it.toStarViewState() }
            .subscribeBy { binding.swipeRefreshLayout.isRefreshing = it.isLoading }
            .addTo(disposables)
    }

    private fun refresh() {
        starActionCreator.fetchStarRepositories().addTo(disposables)
    }

}