package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.SearchedRepo
import com.yuyakaido.android.reduxkit.sample.domain.StarredRepo
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val client: GitHubClient
) {

    fun getAccessToken(code: String): Single<AccessToken> {
        return client.getAccessToken(code)
    }

    fun getSearchedRepositories(query: String): Single<List<SearchedRepo>> {
        return client.getSearchedRepositories(query)
    }

    fun getStarredRepositories(): Single<List<StarredRepo>> {
        return client.getStarredRepositories()
    }

    fun getUser(): Single<Owner> {
        return client.getUser()
    }

}