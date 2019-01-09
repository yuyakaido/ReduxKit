package com.yuyakaido.android.reduxkit.sample.domain

data class SearchedRepo(
    override val repo: Repo,
    override val isStarred: Boolean
) : StarrableRepo