package com.yuyakaido.android.reduxkit.sample.app.actioncreator

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.AsyncActionType
import com.yuyakaido.android.reduxkit.middleware.thunk.Dispatcher
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StarActionCreator @Inject constructor(
  private val repository: GitHubRepository
) {

  fun fetchStarredRepositories(): AsyncActionType {
    return object : AsyncActionType {
      override fun execute(dispatcher: Dispatcher): Single<ActionType> {
        return repository.getStarredRepositories()
          .doOnSubscribe { dispatcher.dispatch(AppAction.StarAction.RefreshRepos(emptyList())) }
          .doOnSubscribe { dispatcher.dispatch(AppAction.StarAction.RefreshLoading(true)) }
          .doOnEvent { _, _ -> dispatcher.dispatch(AppAction.StarAction.RefreshLoading(false)) }
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSuccess { repos -> dispatcher.dispatch(AppAction.DomainAction.PutRepos(repos)) }
          .map { repos -> AppAction.StarAction.RefreshRepos(repos) }
      }
    }
  }

  fun addStar(repo: Repo): AsyncActionType {
    return object : AsyncActionType {
      override fun execute(dispatcher: Dispatcher): Single<ActionType> {
        return repository.addStar(repo)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSuccess { repo -> dispatcher.dispatch(AppAction.DomainAction.PutRepo(repo)) }
          .map { repo -> AppAction.StarAction.AddRepo(repo) }
      }
    }
  }

  fun removeStar(repo: Repo): AsyncActionType {
    return object : AsyncActionType {
      override fun execute(dispatcher: Dispatcher): Single<ActionType> {
        return repository.removeStar(repo)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSuccess { repo -> dispatcher.dispatch(AppAction.DomainAction.PutRepo(repo)) }
          .map { repo -> AppAction.StarAction.RemoveRepo(repo) }
      }
    }
  }

}