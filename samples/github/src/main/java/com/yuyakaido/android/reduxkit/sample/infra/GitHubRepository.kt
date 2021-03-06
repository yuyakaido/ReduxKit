package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.api.client.GitHubClient
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepository @Inject constructor(
  private val client: GitHubClient
) {

  fun getAccessToken(code: String): Single<AccessToken> {
    return client.getAccessToken(code)
  }

  fun searchRepositories(query: String): Single<List<Repo>> {
    return client.searchRepositories(query)
  }

  fun getStarredRepositories(): Single<List<Repo>> {
    return client.getStarredRepositories()
  }

  fun getUser(): Single<Owner> {
    return client.getUser()
  }

  fun addStar(repo: Repo): Single<Repo> {
    return client.addStar(repo)
  }

  fun removeStar(repo: Repo): Single<Repo> {
    return client.removeStar(repo)
  }

}