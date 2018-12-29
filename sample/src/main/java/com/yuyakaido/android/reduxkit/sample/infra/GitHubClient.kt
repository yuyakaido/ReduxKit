package com.yuyakaido.android.reduxkit.sample.infra

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.yuyakaido.android.reduxkit.sample.BuildConfig
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class GitHubClient(
    private val context: Context
) {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .addInterceptor(GitHubInterceptor(context))
        .addNetworkInterceptor(StethoInterceptor())
        .build()
    private val oauthRetrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://github.com/login/oauth/")
        .build()
    private val apiRetrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.github.com/")
        .build()
    private val oauthService = oauthRetrofit.create(GitHubOauthService::class.java)
    private val apiService = apiRetrofit.create(GitHubApiService::class.java)

    fun getAccessToken(code: String): Single<AccessToken> {
        return oauthService.getAccessToken(
            clientId = BuildConfig.CLIENT_ID,
            clientSecret = BuildConfig.CLIENT_SECRET,
            code = code)
            .map { AccessToken(it.value) }
            .singleOrError()
    }

    fun searchRepositoriesByQuery(query: String): Single<List<Repo>> {
        return apiService.searchRepositoriesByQuery(query)
            .map { it.items }
            .singleOrError()
    }

    fun getUser(): Single<Owner> {
        return apiService.getUser()
            .singleOrError()
    }

    interface GitHubOauthService {
        @FormUrlEncoded
        @Headers("Accept: application/json")
        @POST("access_token")
        fun getAccessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("code") code: String
        ): Observable<AccessTokenResponse>
    }

    interface GitHubApiService {
        @GET("search/repositories")
        fun searchRepositoriesByQuery(
            @Query("q") query: String
        ): Observable<SearchResponse>

        @GET("user")
        fun getUser(): Observable<Owner>
    }

}