package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yuyakaido.android.reduxkit.sample.AppAction
import com.yuyakaido.android.reduxkit.sample.R
import com.yuyakaido.android.reduxkit.sample.domain.Owner
import com.yuyakaido.android.reduxkit.sample.infra.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var disposables: CompositeDisposable

    @Inject
    lateinit var gitHubRepository: GitHubRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        disposables = CompositeDisposable()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    override fun onDestroyView() {
        disposables.dispose()
        super.onDestroyView()
    }

    private fun setup(view: View) {
        appStore.observable()
            .filter { it.user.hasValue() }
            .map { it.user.value }
            .cast(Owner::class.java)
            .subscribeBy { user -> setupProfile(view, user) }
            .addTo(disposables)

        gitHubRepository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { user -> appStore.dispatch(AppAction.ReplaceUser(user)) }
            .addTo(disposables)
    }

    private fun setupProfile(view: View, user: Owner) {
        // Avatar
        val avatar = view.findViewById<ImageView>(R.id.avatar)
        Glide.with(requireContext())
            .load(user.avatarUrl)
            .into(avatar)
        // Login
        val login = view.findViewById<TextView>(R.id.login)
        login.text = "ID: ${user.login}"
        // Name
        val name = view.findViewById<TextView>(R.id.name)
        name.text = "Name: ${user.name}"
        // Bio
        val bio = view.findViewById<TextView>(R.id.bio)
        bio.text = "Bio: ${user.bio}"
        // Company
        val company = view.findViewById<TextView>(R.id.company)
        company.text = "Company: ${user.company}"
        // Location
        val location = view.findViewById<TextView>(R.id.location)
        location.text = "Location: ${user.location}"
        // Blog
        val blog = view.findViewById<TextView>(R.id.blog)
        blog.text = "Blog: ${user.blog}"
    }

}