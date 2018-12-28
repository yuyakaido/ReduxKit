package com.yuyakaido.android.reduxkit.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuyakaido.android.reduxkit.server.DevToolServer
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val disposables = CompositeDisposable()
    private val store = AppStore()

    private val server by lazy { DevToolServer(this, store) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDevToolServer()
        setupSearchFragment()
    }

    override fun onDestroy() {
        server.stop()
        disposables.dispose()
        super.onDestroy()
    }

    private fun setupDevToolServer() {
        server.start()
    }

    private fun setupSearchFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, SearchFragment.newInstance())
        transaction.commit()
    }

}
