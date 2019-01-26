package com.yuyakaido.android.reduxkit.sample.infra.api.response

import com.google.gson.annotations.SerializedName
import com.yuyakaido.android.reduxkit.sample.domain.Owner

data class OwnerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
) {

    fun toOwner(): Owner {
        return Owner(
            id = id,
            login = login,
            name = name,
            bio = bio,
            avatarUrl = avatarUrl,
            company = company,
            location = location,
            blog = blog,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

}