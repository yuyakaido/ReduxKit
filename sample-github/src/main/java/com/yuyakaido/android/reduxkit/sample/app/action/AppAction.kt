package com.yuyakaido.android.reduxkit.sample.app.action

import com.yuyakaido.android.reduxkit.core.ActionType
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo

sealed class AppAction : ActionType {

  sealed class DomainAction : AppAction() {
    data class RefreshUser(val user: Owner) : DomainAction()
    data class PutRepos(val repos: List<Repo>) : DomainAction()
    data class StarRepo(val repo: Repo) : DomainAction()
    data class UnstarRepo(val repo: Repo) : DomainAction()
  }

  sealed class SearchAction : AppAction() {
    data class RefreshLoading(val isLoading: Boolean) : SearchAction()
    data class RefreshRepos(val repos: List<Repo>) : SearchAction()
  }

  sealed class StarAction : AppAction() {
    data class RefreshLoading(val isLoading: Boolean) : StarAction()
    data class RefreshRepos(val repos: List<Repo>) : StarAction()
  }

}