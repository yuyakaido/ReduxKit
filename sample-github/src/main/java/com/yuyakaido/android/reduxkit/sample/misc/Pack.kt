package com.yuyakaido.android.reduxkit.sample.misc

data class Pack<T>(
  val value: T
) {

  fun hasValue(): Boolean {
    return value != null
  }

}