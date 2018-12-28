package com.yuyakaido.android.reduxkit.sample.infra

import com.google.gson.annotations.SerializedName
import com.yuyakaido.android.reduxkit.sample.domain.Repo

data class SearchResponse(
    @SerializedName("items") val items: List<Repo>
)