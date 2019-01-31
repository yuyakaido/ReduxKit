package com.yuyakaido.android.reduxkit.sample.infra

import com.google.gson.annotations.SerializedName
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.infra.api.response.RepoResponse

data class SearchResponse(
  @SerializedName("items") val items: List<RepoResponse>
) {

  fun toRepos(): List<Repo> {
    return items.map { it.toRepo(isStarred = false) }
  }

}