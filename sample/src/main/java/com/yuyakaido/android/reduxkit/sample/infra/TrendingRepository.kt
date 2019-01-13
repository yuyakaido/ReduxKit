package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.domain.TrendingRepo
import com.yuyakaido.android.reduxkit.sample.infra.api.client.TrendingClient
import io.reactivex.Single
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val client: TrendingClient
) {

    fun getRepositories(): Single<List<TrendingRepo>> {
        return client.getRepositories()
    }

}