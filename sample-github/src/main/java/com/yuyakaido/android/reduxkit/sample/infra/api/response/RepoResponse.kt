package com.yuyakaido.android.reduxkit.sample.infra.api.response

import com.google.gson.annotations.SerializedName
import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class RepoResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: OwnerResponse,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("language") val language: String?
) {

    fun toRepo(isStarred: Boolean): Repo {
        return Repo(
            id = id,
            name = name,
            fullName = fullName,
            owner = owner.toOwner(),
            htmlUrl = htmlUrl,
            description = description,
            stargazersCount = stargazersCount,
            language = language,
            isStarred = isStarred
        )
    }

}