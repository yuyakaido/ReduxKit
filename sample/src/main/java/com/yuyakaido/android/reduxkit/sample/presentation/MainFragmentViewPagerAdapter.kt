package com.yuyakaido.android.reduxkit.sample.presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainFragmentViewPagerAdapter(
    manager: FragmentManager
) : FragmentPagerAdapter(manager) {

    enum class Page {
        Search() {
            override fun fragment(): Fragment {
                return SearchFragment.newInstance()
            }
        },
        Profile() {
            override fun fragment(): Fragment {
                return ProfileFragment.newInstance()
            }
        };

        abstract fun fragment(): Fragment
    }

    override fun getCount(): Int {
        return Page.values().size
    }

    override fun getItem(position: Int): Fragment {
        return Page.values()[position].fragment()
    }

}