package com.yuyakaido.android.reduxkit.sample.infra

import com.yuyakaido.android.reduxkit.sample.BuildConfig
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Inject

class GitHubClient @Inject constructor(
    private val authService: GitHubAuthService,
    private val apiService: GitHubApiService
) {

    fun getAccessToken(code: String): Single<AccessToken> {
        return authService.getAccessToken(
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

    interface GitHubAuthService {
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