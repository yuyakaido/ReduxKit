package com.yuyakaido.android.reduxkit.sample.infra.api.response

import com.google.gson.annotations.SerializedName
import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class RepoResponse(
  @SerializedName("id") val id: Long,
  @SerializedName("name") val name: String,
  @SerializedName("owner") val owner: OwnerResponse,
  @SerializedName("full_name") val fullName: String,
  @SerializedName("description") val description: String?,
  @SerializedName("language") val language: String?,
  @SerializedName("stargazers_count") val stargazersCount: Int
) {

  fun toRepo(isStarred: Boolean): Repo {
    return Repo(
      id = id,
      name = name,
      owner = owner.toOwner(),
      fullName = fullName,
      description = description,
      language = language,
      stargazersCount = stargazersCount,
      isStarred = isStarred
    )
  }

}