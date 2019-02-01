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

class ProfileActionCreator @Inject constructor(
  private val repository: GitHubRepository
) {

  fun fetchUser(): AsyncActionType {
    return object : AsyncActionType {
      override fun execute(dispatcher: Dispatcher): Single<ActionType> {
        return repository.getUser()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .map { AppAction.DomainAction.RefreshUser(it) }
      }
    }
  }

}