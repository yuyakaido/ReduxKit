package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.Single

class GitHubRepository {

    private val client = GitHubClient()

    fun searchRepositoriesByQuery(query: String): Single<List<Repo>> {
        return client.searchRepositoriesByQuery(query)
    }

}