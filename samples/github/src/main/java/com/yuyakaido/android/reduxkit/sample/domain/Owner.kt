package com.yuyakaido.android.reduxkit.sample.domain

data class Owner(
  val login: String,      // yuyakaido
  val name: String?,      // Yuya Kaido
  val bio: String?,       // Android Engineer
  val company: String?,   // eureka, Inc.
  val location: String?,  // Tokyo, Japan
  val avatarUrl: String,  // https://avatars0.githubusercontent.com/u/6598880?v=4
  val websiteUrl: String? // http://yuyakaido.hatenablog.com/
)