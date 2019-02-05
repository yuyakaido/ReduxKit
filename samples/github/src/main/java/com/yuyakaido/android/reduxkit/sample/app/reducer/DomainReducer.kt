package com.yuyakaido.android.reduxkit.sample.app.reducer

import com.yuyakaido.android.reduxkit.core.ReducerType
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.app.state.DomainState
import com.yuyakaido.android.reduxkit.sample.misc.Pack

object DomainReducer : ReducerType<DomainState, AppAction.DomainAction> {

  override fun reduce(state: DomainState, action: AppAction.DomainAction): DomainState {
    return when (action) {
      is AppAction.DomainAction.RefreshUser -> {
        state.copy(user = Pack(action.user))
      }
      is AppAction.DomainAction.PutRepos -> {
        state.apply { repos.apply { putAll(action.repos.associate { it.id to it }) } }
      }
      is AppAction.DomainAction.StarRepo -> {
        state.apply { repos[action.repo.id] = action.repo.copy(isStarred = true) }
      }
      is AppAction.DomainAction.UnstarRepo -> {
        state.apply { repos[action.repo.id] = action.repo.copy(isStarred = false) }
      }
    }
  }

}