package com.yuyakaido.android.reduxkit.sample.domain

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)