package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.databinding.FragmentRepositoryBinding
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryFragment : BaseFragment() {

    companion object {
        fun newInstance() = RepositoryFragment()
    }

    private lateinit var disposables: CompositeDisposable
    private lateinit var binding: FragmentRepositoryBinding

    @Inject
    lateinit var gitHubRepository: GitHubRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        disposables = CompositeDisposable()
        binding = FragmentRepositoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
    }

    override fun onDestroyView() {
        disposables.dispose()
        super.onDestroyView()
    }

    private fun setupRecyclerView(view: View) {
        val adapter = RepoAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        appStore.observable()
            .map { it.session.ownRepos }
            .subscribeBy { repos ->
                adapter.setRepos(repos)
                adapter.notifyDataSetChanged()
            }
            .addTo(disposables)

        gitHubRepository.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { repos ->
                appStore.dispatch(AppAction.SessionAction.ReplaceOwnRepos(repos))
            }
            .addTo(disposables)
    }

}