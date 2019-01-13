package com.yuyakaido.android.reduxkit.sample.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yuyakaido.android.reduxkit.sample.R
import com.yuyakaido.android.reduxkit.sample.domain.TrendingRepo

class TrendingRepoAdapter(
    private val repos: MutableList<TrendingRepo> = mutableListOf(),
    private val listener: OnStarClickListener? = null
) : RecyclerView.Adapter<TrendingRepoAdapter.ViewHolder>() {

    interface OnStarClickListener {
        fun onStarClick(repo: TrendingRepo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(inflate.inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        holder.name.text = "${repo.author}/${repo.name}"
        holder.star.setOnClickListener { listener?.onStarClick(repo) }
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    fun setTrendingRepos(repos: List<TrendingRepo>) {
        this.repos.clear()
        this.repos.addAll(repos)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val star: ImageView = view.findViewById(R.id.star)
    }

}