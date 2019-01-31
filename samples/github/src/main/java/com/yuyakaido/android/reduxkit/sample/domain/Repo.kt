package com.yuyakaido.android.reduxkit.sample.domain

data class Repo(
  val id: Long,
  val name: String,
  val fullName: String,
  val owner: Owner,
  val htmlUrl: String,
  val description: String?,
  val stargazersCount: Int,
  val language: String?,
  val isStarred: Boolean
)