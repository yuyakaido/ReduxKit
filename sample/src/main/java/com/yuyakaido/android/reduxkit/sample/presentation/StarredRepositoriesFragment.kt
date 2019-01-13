package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.databinding.FragmentStarredRepositoriesBinding
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StarredRepositoriesFragment : BaseFragment(), RepoAdapter.OnStarClickListener {

    companion object {
        fun newInstance() = StarredRepositoriesFragment()
    }

    private lateinit var disposables: CompositeDisposable
    private lateinit var binding: FragmentStarredRepositoriesBinding

    @Inject
    lateinit var gitHubRepository: GitHubRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        disposables = CompositeDisposable()
        binding = FragmentStarredRepositoriesBinding.inflate(inflater)
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

    override fun onStarClick(repo: Repo) {
        if (repo.isStarred) {
            gitHubRepository.unstarRepo(repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    appStore.dispatch(AppAction.SessionAction.DomainAction.UnstarRepo(it))
                }
                .addTo(disposables)
        } else {
            gitHubRepository.starRepo(repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    appStore.dispatch(AppAction.SessionAction.DomainAction.StarRepo(it))
                }
                .addTo(disposables)
        }
    }

    private fun setupRecyclerView() {
        val adapter = RepoAdapter(listener = this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        appStore.observable()
            .map { it.session.toStarredReposState() }
            .subscribeBy { state ->
                adapter.setRepos(state.repos)
                adapter.notifyDataSetChanged()
            }
            .addTo(disposables)

        gitHubRepository.getStarredRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { appStore.dispatch(AppAction.SessionAction.ReplaceStarredRepos(it)) }
            .addTo(disposables)
    }

}