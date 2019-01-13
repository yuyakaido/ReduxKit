package com.yuyakaido.android.reduxkit.sample.presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yuyakaido.android.reduxkit.sample.R

class MainFragmentViewPagerAdapter(
    manager: FragmentManager
) : FragmentPagerAdapter(manager) {

    enum class Page(
        private val menuId: Int
    ) {
        SearchRepositories(
            menuId = R.id.navigation_discovery
        ) {
            override fun fragment(): Fragment {
                return SearchRepositoriesFragment.newInstance()
            }
        },
        StarredRepositories(
            menuId = R.id.navigation_star
        ) {
            override fun fragment(): Fragment {
                return StarredRepositoriesFragment.newInstance()
            }
        },
        Profile(
            menuId = R.id.navigation_profile
        ) {
            override fun fragment(): Fragment {
                return ProfileFragment.newInstance()
            }
        };

        companion object {
            fun fromMenuId(menuId: Int): Page {
                return values().first { it.menuId == menuId }
            }
        }

        abstract fun fragment(): Fragment
    }

    override fun getCount(): Int {
        return Page.values().size
    }

    override fun getItem(position: Int): Fragment {
        return Page.values()[position].fragment()
    }

}