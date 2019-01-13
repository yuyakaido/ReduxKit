package com.yuyakaido.android.reduxkit.sample.di.module

import com.yuyakaido.android.reduxkit.sample.presentation.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PresentationModule {

    @ContributesAndroidInjector
    abstract fun contributeLauncherActivity(): LauncherActivity

    @ContributesAndroidInjector
    abstract fun contributeLaunchAuthorizeActivity(): LaunchAuthorizeActivity

    @ContributesAndroidInjector
    abstract fun contributeCompleteAuthorizeActivity(): CompleteAuthorizeActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSearchRepositoryFragment(): SearchRepositoryFragment

    @ContributesAndroidInjector
    abstract fun contributeTrendingFragment(): TrendingRepositoryFragment

    @ContributesAndroidInjector
    abstract fun contributeStarRepositoryFragment(): StarRepositoryFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

}