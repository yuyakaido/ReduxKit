package com.yuyakaido.android.reduxkit.sample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yuyakaido.android.reduxkit.sample.R
import com.yuyakaido.android.reduxkit.sample.app.action.AppAction
import com.yuyakaido.android.reduxkit.sample.databinding.FragmentProfileBinding
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
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var gitHubRepository: GitHubRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        disposables = CompositeDisposable()
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun onDestroyView() {
        disposables.dispose()
        super.onDestroyView()
    }

    private fun setup() {
        appStore.observable()
            .filter { it.domain.user.hasValue() }
            .map { it.domain.user.value }
            .cast(Owner::class.java)
            .subscribeBy { user -> setupProfile(user) }
            .addTo(disposables)

        gitHubRepository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { user -> appStore.dispatch(AppAction.DomainAction.RefreshUser(user)) }
            .addTo(disposables)
    }

    private fun setupProfile(user: Owner) {
        Glide.with(requireContext()).load(user.avatarUrl).into(binding.avatar)
        binding.login.text = getString(R.string.profile_id, user.login)
        binding.name.text = getString(R.string.profile_name, user.name)
        binding.bio.text = getString(R.string.profile_bio, user.bio)
        binding.company.text = getString(R.string.profile_company, user.company)
        binding.location.text = getString(R.string.profile_location, user.location)
        binding.blog.text = getString(R.string.profile_blog, user.blog)
    }

}