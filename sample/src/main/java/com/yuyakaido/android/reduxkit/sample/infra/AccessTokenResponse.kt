package com.yuyakaido.android.reduxkit.sample.infra

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token") val value: String,
    @SerializedName("scope") val scope: String,
    @SerializedName("token_type") val type: String
)