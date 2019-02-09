package com.yuyakaido.android.reduxkit.sample.app.actioncreator

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.AsyncActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.Dispatcher
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchActionCreator @Inject constructor(
  private val repository: GitHubRepository
) {

  fun fetchSearchRepositories(query: String): AsyncActionType {
    return object : AsyncActionType {
      override fun execute(dispatcher: Dispatcher): Single<ActionType> {
        return repository.searchRepositories(query)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe { dispatcher.dispatch(AppAction.SearchAction.RefreshLoading(true)) }
          .doOnEvent { _, _ -> dispatcher.dispatch(AppAction.SearchAction.RefreshLoading(false)) }
          .onErrorReturn { emptyList() }
          .doOnSuccess { repos -> dispatcher.dispatch(AppAction.DomainAction.PutRepos(repos)) }
          .map { repos -> AppAction.SearchAction.RefreshRepos(repos) }
      }
    }
  }

}