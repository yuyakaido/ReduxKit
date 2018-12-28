package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class GitHubClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.github.com/")
        .build()
    private val service = retrofit.create(GitHubService::class.java)

    fun searchRepositoriesByQuery(query: String): Single<List<Repo>> {
        return service.searchRepositoriesByQuery(query)
            .map { it.items }
            .singleOrError()
    }

    interface GitHubService {
        @GET("search/repositories")
        fun searchRepositoriesByQuery(
            @Query("q") query: String
        ): Observable<SearchResponse>
    }

}