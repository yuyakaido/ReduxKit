package com.yuyakaido.android.reduxkit.sample.infra

import android.content.Context
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.Single

class GitHubRepository(
    private val context: Context
) {

    private val client = GitHubClient(context)

    fun getAccessToken(code: String): Single<AccessToken> {
        return client.getAccessToken(code)
    }

    fun searchRepositoriesByQuery(query: String): Single<List<Repo>> {
        return client.searchRepositoriesByQuery(query)
    }

    fun getUser(): Single<Owner> {
        return client.getUser()
    }

}