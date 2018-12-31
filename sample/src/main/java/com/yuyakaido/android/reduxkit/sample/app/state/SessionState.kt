package com.yuyakaido.android.reduxkit.sample.app.state

import com.yuyakaido.android.reduxkit.core.StateType
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.domain.Repo
import com.yuyakaido.android.reduxkit.sample.misc.Pack

data class SessionState(
    val user: Pack<Owner?> = Pack(null),
    val ownRepos: List<Repo> = emptyList(),
    val searchedRepos: MutableMap<String, List<Repo>> = mutableMapOf()
) : StateType