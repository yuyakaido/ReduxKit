package com.yuyakaido.android.reduxkit.sample.domain

import android.content.Context
import android.preference.PreferenceManager

data class AccessToken(
    val value: String
) {

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"

        fun getOrNull(context: Context): AccessToken? {
            val preference = PreferenceManager.getDefaultSharedPreferences(context)
            val value = preference.getString(ACCESS_TOKEN, null)
            return if (value == null) {
                null
            } else {
                AccessToken(value)
            }
        }

        fun put(context: Context, token: AccessToken) {
            val preference = PreferenceManager.getDefaultSharedPreferences(context)
            preference.edit().apply {
                putString(ACCESS_TOKEN, token.value)
                apply()
            }
        }
    }

}