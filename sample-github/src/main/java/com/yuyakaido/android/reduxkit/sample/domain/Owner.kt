package com.yuyakaido.android.reduxkit.sample.domain

data class Owner(
    val id: Long,
    val login: String,
    val name: String?,
    val bio: String?,
    val avatarUrl: String?,
    val company: String?,
    val location: String?,
    val blog: String?,
    val createdAt: String?,
    val updatedAt: String?
)