package com.yuyakaido.android.reduxkit.sample

import com.yuyakaido.android.reduxkit.core.StateType
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.misc.Pack

data class AppState(
    val user: Pack<Owner?> = Pack(null),
    val repos: List<Repo> = emptyList()
) : StateType