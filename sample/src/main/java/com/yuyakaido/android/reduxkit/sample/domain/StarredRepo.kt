package com.yuyakaido.android.reduxkit.sample.domain

data class StarredRepo(
    override val repo: Repo,
    override val isStarred: Boolean
) : StarrableRepo