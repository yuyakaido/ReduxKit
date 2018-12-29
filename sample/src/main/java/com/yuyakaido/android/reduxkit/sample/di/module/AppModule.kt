package com.yuyakaido.android.reduxkit.sample.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.yuyakaido.android.reduxkit.sample.AppStore
import com.yuyakaido.android.reduxkit.sample.app.ReduxKit
import com.yuyakaido.android.reduxkit.sample.di.annotation.AppScope
import com.yuyakaido.android.reduxkit.sample.di.annotation.GitHubApiRetrofit
import com.yuyakaido.android.reduxkit.sample.di.annotation.GitHubAuthRetrofit
import com.yuyakaido.android.reduxkit.sample.infra.GitHubClient
import com.yuyakaido.android.reduxkit.sample.infra.GitHubInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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

    @GitHubAuthRetrofit
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

    @GitHubApiRetrofit
    @AppScope
    @Provides
    fun provideGitHubApiRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()
    }

    @AppScope
    @Provides
    fun provideGitHubAuthService(
        @GitHubAuthRetrofit retrofit: Retrofit
    ): GitHubClient.GitHubAuthService {
        return retrofit.create(GitHubClient.GitHubAuthService::class.java)
    }

    @AppScope
    @Provides
    fun provideGitHubApiService(
        @GitHubApiRetrofit retrofit: Retrofit
    ): GitHubClient.GitHubApiService {
        return retrofit.create(GitHubClient.GitHubApiService::class.java)
    }

}