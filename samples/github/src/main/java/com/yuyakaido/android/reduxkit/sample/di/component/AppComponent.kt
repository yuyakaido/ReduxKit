package com.yuyakaido.android.reduxkit.sample.di.component

import com.yuyakaido.android.reduxkit.sample.app.ReduxKit
import com.yuyakaido.android.reduxkit.sample.di.annotation.AppScope
import com.yuyakaido.android.reduxkit.sample.di.module.AppModule
import com.yuyakaido.android.reduxkit.sample.di.module.PresentationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    PresentationModule::class
  ]
)
interface AppComponent : AndroidInjector<ReduxKit> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<ReduxKit>()

}