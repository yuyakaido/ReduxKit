package com.yuyakaido.android.reduxkit.sample.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.yuyakaido.android.reduxkit.sample.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDevToolServer()
        setupViewPager()
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
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = MainFragmentViewPagerAdapter(supportFragmentManager)
    }

}
