package com.yuyakaido.android.reduxkit.sample.di.module

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.response.CustomTypeAdapter
import com.apollographql.apollo.response.CustomTypeValue
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.yuyakaido.android.reduxkit.sample.app.ReduxKit
import com.yuyakaido.android.reduxkit.sample.app.store.AppStore
import com.yuyakaido.android.reduxkit.sample.di.annotation.AppScope
import com.yuyakaido.android.reduxkit.sample.infra.GitHubInterceptor
import com.yuyakaido.android.reduxkit.sample.infra.api.client.GitHubClient
import com.yuyakaido.android.reduxkit.sample.type.CustomType
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URI

@Module
class AppModule {

  @AppScope
  @Provides
  fun provideApplication(application: ReduxKit): Application {
    return application
  }

  @AppScope
  @Provides
  fun provideAppStore(): AppStore {
    return AppStore()
  }

  @AppScope
  @Provides
  fun provideOkHttpClient(application: Application): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .addInterceptor(GitHubInterceptor(application))
      .addNetworkInterceptor(StethoInterceptor())
      .build()
  }

  @AppScope
  @Provides
  fun provideApolloClient(client: OkHttpClient): ApolloClient {
    val uriAdapter = object : CustomTypeAdapter<URI> {
      override fun decode(value: CustomTypeValue<*>): URI {
        return URI(value.value as String)
      }
      override fun encode(value: URI): CustomTypeValue<*> {
        return CustomTypeValue.GraphQLString(value.toString())
      }
    }
    return ApolloClient.builder()
      .okHttpClient(client)
      .serverUrl("https://api.github.com/graphql")
      .addCustomTypeAdapter(CustomType.URI, uriAdapter)
      .build()
  }

  @AppScope
  @Provides
  fun provideGitHubAuthRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl("https://github.com/login/oauth/")
      .build()
  }

  @AppScope
  @Provides
  fun provideGitHubAuthService(
    retrofit: Retrofit
  ): GitHubClient.AuthService {
    return retrofit.create(GitHubClient.AuthService::class.java)
  }

}