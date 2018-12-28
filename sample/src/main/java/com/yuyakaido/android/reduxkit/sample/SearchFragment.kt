package com.yuyakaido.android.reduxkit.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var store: AppStore
    private lateinit var disposables: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        store = AppStore()
        disposables = CompositeDisposable()
        return inflater.inflate(R.layout.fragment_search, container, false)
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
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        store.observable()
            .map { it.repos }
            .subscribeBy { repos ->
                adapter.setRepos(repos)
                adapter.notifyDataSetChanged()
            }
            .addTo(disposables)

        GitHubRepository()
            .searchRepositoriesByQuery(query = "CardStackView")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { repos ->
                store.dispatch(AppAction.ReplaceRepo(repos))
            }
            .addTo(disposables)
    }

}