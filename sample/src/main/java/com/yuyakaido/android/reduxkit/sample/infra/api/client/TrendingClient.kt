package com.yuyakaido.android.reduxkit.sample.infra.api.client

import com.yuyakaido.android.reduxkit.sample.domain.TrendingRepo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Inject

class TrendingClient @Inject constructor(
    private val service: TrendingService
) {

    fun getRepositories(): Single<List<TrendingRepo>> {
        return service.getRepositories()
            .singleOrError()
    }

    interface TrendingService {
        @GET("repositories")
        fun getRepositories(): Observable<List<TrendingRepo>>
    }

}