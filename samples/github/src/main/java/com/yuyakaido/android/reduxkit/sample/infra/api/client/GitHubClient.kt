package com.yuyakaido.android.reduxkit.sample.infra.api.client

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.yuyakaido.android.reduxkit.sample.*
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.AccessTokenResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Inject

class GitHubClient @Inject constructor(
  private val authService: AuthService,
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

  fun searchRepositories(query: String): Single<List<Repo>> {
    return Rx2Apollo.from(apolloClient.query(SearchRepositoryQuery(query)).watcher())
      .map { response -> response.data()?.search()?.edges() }
      .map { edges -> edges.map { edge -> edge.node() as SearchRepositoryQuery.AsRepository } }
      .map { repos ->
        repos.map { repo ->
          val owner = repo.owner()
          Repo(
            id = repo.id(),
            name = repo.name(),
            owner = Owner(
              login = owner.login(),
              name = null,
              bio = null,
              company = null,
              location = null,
              avatarUrl = owner.avatarUrl().toString(),
              websiteUrl = null
            ),
            nameWithOwner = repo.nameWithOwner(),
            description = repo.description(),
            primaryLanguage = repo.primaryLanguage()?.name(),
            stargazersTotalCount = repo.stargazers().totalCount(),
            hasStarred = repo.viewerHasStarred()
          )
        }
      }
      .firstOrError()
  }

  fun getStarredRepositories(): Single<List<Repo>> {
    return Rx2Apollo.from(apolloClient.query(GetStarredRepositoryQuery()).watcher())
      .map { response -> response.data()?.viewer()?.starredRepositories()?.edges() }
      .map { edges -> edges.map { edge -> edge.node() } }
      .map { repos ->
        repos.map { repo ->
          val owner = repo.owner()
          Repo(
            id = repo.id(),
            name = repo.name(),
            owner = Owner(
              login = owner.login(),
              name = null,
              bio = null,
              company = null,
              location = null,
              avatarUrl = owner.avatarUrl().toString(),
              websiteUrl = null
            ),
            nameWithOwner = repo.nameWithOwner(),
            description = repo.description(),
            primaryLanguage = repo.primaryLanguage()?.name(),
            stargazersTotalCount = repo.stargazers().totalCount(),
            hasStarred = repo.viewerHasStarred()
          )
        }
      }
      .firstOrError()
  }

  fun getUser(): Single<Owner> {
    return Rx2Apollo.from(apolloClient.query(GetViewerQuery()).watcher())
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

  fun addStar(repo: Repo): Single<Repo> {
    return Rx2Apollo.from(apolloClient.mutate(AddStarMutation(repo.id)))
      .map { repo.copy(hasStarred = true) }
      .firstOrError()
  }

  fun removeStar(repo: Repo): Single<Repo> {
    return Rx2Apollo.from(apolloClient.mutate(RemoveStarMutation(repo.id)))
      .map { repo.copy(hasStarred = false) }
      .firstOrError()
  }

  interface AuthService {
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("access_token")
    fun getAccessToken(
      @Field("client_id") clientId: String,
      @Field("client_secret") clientSecret: String,
      @Field("code") code: String
    ): Observable<AccessTokenResponse>
  }

}