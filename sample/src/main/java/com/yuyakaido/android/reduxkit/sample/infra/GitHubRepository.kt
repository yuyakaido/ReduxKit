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

    fun searchRepositoriesByQuery(query: String): Single<List<Repo>> {
        return client.searchedRepositoriesByQuery(query)
    }

    fun getStarredRepositories(): Single<List<Repo>> {
        return client.getStarredRepositories()
    }

    fun getUser(): Single<Owner> {
        return client.getUser()
    }

    fun starRepo(repo: Repo): Single<Repo> {
        return client.starRepo(repo)
    }

    fun unstarRepo(repo: Repo): Single<Repo> {
        return client.unstarRepo(repo)
    }

}