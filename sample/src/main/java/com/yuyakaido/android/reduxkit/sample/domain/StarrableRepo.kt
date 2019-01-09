package com.yuyakaido.android.reduxkit.sample.domain

interface StarrableRepo {
    val repo: Repo
    val isStarred: Boolean
}