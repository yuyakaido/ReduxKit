package com.yuyakaido.android.reduxkit.sample.infra.api.response

import com.google.gson.annotations.SerializedName
import com.yuyakaido.android.reduxkit.sample.domain.Owner

data class OwnerResponse(
  @SerializedName("login") val login: String,
  @SerializedName("name") val name: String?,
  @SerializedName("bio") val bio: String?,
  @SerializedName("company") val company: String?,
  @SerializedName("location") val location: String?,
  @SerializedName("avatar_url") val avatarUrl: String,
  @SerializedName("blog") val blog: String?
) {

  fun toOwner(): Owner {
    return Owner(
      login = login,
      name = name,
      bio = bio,
      company = company,
      location = location,
      avatarUrl = avatarUrl,
      websiteUrl = blog
    )
  }

}