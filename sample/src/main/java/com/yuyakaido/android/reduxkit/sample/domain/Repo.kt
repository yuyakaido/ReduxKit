package com.yuyakaido.android.reduxkit.sample.domain

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("language") val language: String
)