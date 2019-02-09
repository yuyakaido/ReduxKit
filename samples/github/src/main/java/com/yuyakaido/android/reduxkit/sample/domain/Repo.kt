package com.yuyakaido.android.reduxkit.sample.domain

data class Repo(
  val id: String,                 // MDEwOlJlcG9zaXRvcnkxNjI3NDMxMDI=
  val name: String,               // ReduxKit
  val owner: Owner,               // yuyakaido
  val nameWithOwner: String,      // yuyakaido/ReduxKit
  val description: String?,       // ♻️ Redux implementation for Android
  val primaryLanguage: String?,   // Kotlin
  val stargazersTotalCount: Int,  // 7
  val hasStarred: Boolean         // false
)