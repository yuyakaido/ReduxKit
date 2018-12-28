package com.yuyakaido.android.reduxkit.sample.infra

import android.content.Context
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = AccessToken.getOrNull(context)
        val originalRequest = chain.request()
        return if (accessToken == null) {
            chain.proceed(originalRequest)
        } else {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "token ${accessToken.value}")
                .build()
            chain.proceed(newRequest)
        }
    }

}