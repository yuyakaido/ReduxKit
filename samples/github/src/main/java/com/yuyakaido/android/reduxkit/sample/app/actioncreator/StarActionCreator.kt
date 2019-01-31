package com.yuyakaido.android.reduxkit.sample.app.actioncreator

import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StarActionCreator @Inject constructor(
  private val store: AppStore,
  private val repository: GitHubRepository
) {

  fun fetchStarRepositories(): Disposable {
    return repository.getStarredRepositories()
      .doOnSubscribe { store.dispatch(AppAction.StarAction.RefreshRepos(emptyList())) }
      .doOnSubscribe { store.dispatch(AppAction.StarAction.RefreshLoading(true)) }
      .doOnEvent { _, _ -> store.dispatch(AppAction.StarAction.RefreshLoading(false)) }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy { repos ->
        store.dispatch(AppAction.DomainAction.PutRepos(repos))
        store.dispatch(AppAction.StarAction.RefreshRepos(repos))
      }
  }

  fun starRepo(repo: Repo): Disposable {
    return repository.starRepo(repo)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy { store.dispatch(AppAction.DomainAction.StarRepo(it)) }
  }

  fun unstarRepo(repo: Repo): Disposable {
    return repository.unstarRepo(repo)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy { store.dispatch(AppAction.DomainAction.UnstarRepo(it)) }
  }

}