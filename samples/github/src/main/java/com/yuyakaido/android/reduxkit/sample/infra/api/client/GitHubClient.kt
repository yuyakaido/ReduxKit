package com.yuyakaido.android.reduxkit.sample.infra.api.client

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.yuyakaido.android.reduxkit.sample.BuildConfig
import com.yuyakaido.android.reduxkit.sample.ViewerQuery
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.AccessTokenResponse
import com.yuyakaido.android.reduxkit.sample.infra.SearchResponse
import com.yuyakaido.android.reduxkit.sample.infra.api.response.RepoResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject

class GitHubClient @Inject constructor(
  private val authService: GitHubAuthService,
  private val apiService: GitHubApiService,
  private val apolloClient: ApolloClient
) {

  fun getAccessToken(code: String): Single<AccessToken> {
    return authService.getAccessToken(
      clientId = BuildConfig.CLIENT_ID,
      clientSecret = BuildConfig.CLIENT_SECRET,
      code = code
    )
      .map { AccessToken(it.value) }
      .singleOrError()
  }

  fun searchedRepositoriesByQuery(query: String): Single<List<Repo>> {
    return apiService.searchRepositoriesByQuery(query)
      .map { it.toRepos() }
      .singleOrError()
  }

  fun getStarredRepositories(): Single<List<Repo>> {
    return apiService.getStarredRepositories()
      .map { responses ->
        responses.map { response ->
          response.toRepo(isStarred = true)
        }
      }
      .singleOrError()
  }

  fun getUser(): Single<Owner> {
    return Rx2Apollo.from(apolloClient.query(ViewerQuery()).watcher())
      .map { response ->
        val viewer = response.data()!!.viewer()
        Owner(
          login = viewer.login(),
          name = viewer.name(),
          bio = viewer.bio(),
          company = viewer.company(),
          location = viewer.location(),
          avatarUrl = viewer.avatarUrl().toString(),
          websiteUrl = viewer.websiteUrl()?.toString()
        )
      }
      .firstOrError()
  }

  fun starRepo(repo: Repo): Single<Repo> {
    return apiService.starRepo(
      owner = repo.owner.login,
      repo = repo.name
    )
      .map { repo.copy(isStarred = true) }
      .singleOrError()
  }

  fun unstarRepo(repo: Repo): Single<Repo> {
    return apiService.unstarRepo(
      owner = repo.owner.login,
      repo = repo.name
    )
      .map { repo.copy(isStarred = false) }
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

    @GET("user/starred")
    fun getStarredRepositories(): Observable<List<RepoResponse>>

    @PUT("user/starred/{owner}/{repo}")
    fun starRepo(
      @Path("owner") owner: String,
      @Path("repo") repo: String
    ): Observable<Response<Unit>>

    @DELETE("user/starred/{owner}/{repo}")
    fun unstarRepo(
      @Path("owner") owner: String,
      @Path("repo") repo: String
    ): Observable<Response<Unit>>
  }

}