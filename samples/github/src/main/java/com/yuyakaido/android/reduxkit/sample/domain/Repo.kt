package com.yuyakaido.android.reduxkit.sample.domain

data class Repo(
  val id: Long,             // 162743102
  val name: String,         // ReduxKit
  val owner: Owner,         // yuyakaido
  val fullName: String,     // yuyakaido/ReduxKit
  val description: String?, // ♻️ Redux implementation for Android
  val language: String?,    // Kotlin
  val stargazersCount: Int, // 2
  val isStarred: Boolean    // false
)