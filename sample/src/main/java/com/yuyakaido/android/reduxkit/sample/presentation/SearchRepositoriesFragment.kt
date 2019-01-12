package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.databinding.FragmentSearchRepositoriesBinding
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepositoriesFragment : BaseFragment(), RepoAdapter.OnStarClickListener {

    companion object {
        fun newInstance() = SearchRepositoriesFragment()
    }

    private lateinit var disposables: CompositeDisposable
    private lateinit var binding: FragmentSearchRepositoriesBinding

    @Inject
    lateinit var gitHubRepository: GitHubRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        disposables = CompositeDisposable()
        binding = FragmentSearchRepositoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        disposables.dispose()
        super.onDestroyView()
    }

    override fun onStartClick(repo: Repo) {
        if (repo.isStarred) {
            appStore.dispatch(AppAction.SessionAction.DomainAction.UnstarRepo(repo))
        } else {
            appStore.dispatch(AppAction.SessionAction.DomainAction.StarRepo(repo))
        }
    }

    private fun setupRecyclerView() {
        val adapter = RepoAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        val query = "CardStackView"
        appStore.observable()
            .map { it.session.toSearchedReposState() }
            .subscribeBy { state ->
                adapter.setRepos(state.repos)
                adapter.notifyDataSetChanged()
            }
            .addTo(disposables)

        gitHubRepository.getSearchedRepositories(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { appStore.dispatch(AppAction.SessionAction.ReplaceSearchedRepos(query, it)) }
            .addTo(disposables)
    }

}