package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val client: GitHubClient
) {

    fun getAccessToken(code: String): Single<AccessToken> {
        return client.getAccessToken(code)
    }

    fun searchRepositoriesByQuery(query: String): Single<List<Repo>> {
        return client.searchRepositoriesByQuery(query)
    }

    fun getUser(): Single<Owner> {
        return client.getUser()
    }

    fun getRepositories(): Single<List<Repo>> {
        return client.getRepositories()
    }

}