package com.yuyakaido.android.reduxkit.sample.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yuyakaido.android.reduxkit.sample.databinding.ActivityMainBinding
import com.yuyakaido.android.reduxkit.server.DevToolServer
import io.reactivex.disposables.CompositeDisposable

class MainActivity : BaseActivity() {

  companion object {
    fun createIntent(context: Context): Intent {
      return Intent(context, MainActivity::class.java)
    }
  }

  private val disposables = CompositeDisposable()

  private val server by lazy { DevToolServer(this, appStore) }
  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setupDevToolServer()
    setupViewPager()
    setupBottomNavigationView()
  }

  override fun onDestroy() {
    server.stop()
    disposables.dispose()
    super.onDestroy()
  }

  private fun setupDevToolServer() {
    server.start()
  }

  private fun setupViewPager() {
    binding.viewPager.offscreenPageLimit = MainFragmentViewPagerAdapter.Page.values().size - 1
    binding.viewPager.adapter = MainFragmentViewPagerAdapter(supportFragmentManager)
  }

  private fun setupBottomNavigationView() {
    binding.bottomNavigationView.setOnNavigationItemSelectedListener {
      val page = MainFragmentViewPagerAdapter.Page.fromMenuId(it.itemId)
      binding.viewPager.currentItem = page.ordinal
      return@setOnNavigationItemSelectedListener true
    }
  }

}
