package com.yuyakaido.android.reduxkit.sample.app.actioncreator

import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileActionCreator @Inject constructor(
    private val store: AppStore,
    private val repository: GitHubRepository
) {

    fun fetchUser(): Disposable {
        return repository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { store.dispatch(AppAction.DomainAction.RefreshUser(it)) }
    }

}