package com.yuyakaido.android.reduxkit.sample.app.actioncreator

import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchActionCreator @Inject constructor(
    private val store: AppStore,
    private val repository: GitHubRepository
) {

    fun fetchSearchRepositories(query: String): Disposable {
        return repository.searchRepositoriesByQuery(query)
            .doOnSubscribe { store.dispatch(AppAction.SearchAction.RefreshRepos(emptyList())) }
            .doOnSubscribe { store.dispatch(AppAction.SearchAction.RefreshLoading(true)) }
            .doOnEvent { _, _ -> store.dispatch(AppAction.SearchAction.RefreshLoading(false)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { repos ->
                store.dispatch(AppAction.DomainAction.PutRepos(repos))
                store.dispatch(AppAction.SearchAction.RefreshRepos(repos))
            }
    }

}