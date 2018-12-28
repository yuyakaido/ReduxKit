package com.yuyakaido.android.reduxkit.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuyakaido.android.reduxkit.sample.domain.AccessToken
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CompleteAuthorizeActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CompleteAuthorizeActivity::class.java)
        }
    }

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleUrlScheme()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun handleUrlScheme() {
        intent.data?.let { uri ->
            uri.getQueryParameter("code")?.let { code ->
                GitHubRepository()
                    .getAccessToken(code)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy { token ->
                        AccessToken.put(this, token)
                        startActivity(MainActivity.createIntent(this))
                        finish()
                    }
                    .addTo(disposables)
            }
        }
    }

}