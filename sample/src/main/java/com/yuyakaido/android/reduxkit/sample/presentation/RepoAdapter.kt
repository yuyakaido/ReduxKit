package com.yuyakaido.android.reduxkit.sample.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yuyakaido.android.reduxkit.sample.R
import com.yuyakaido.android.reduxkit.sample.domain.Repo

class RepoAdapter(
    private val repos: MutableList<Repo> = mutableListOf(),
    private val listener: OnStarClickListener? = null
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    interface OnStarClickListener {
        fun onStartClick(repo: Repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(inflate.inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        holder.name.text = repo.fullName
        holder.star.setOnClickListener { listener?.onStartClick(repo) }
        if (repo.isStarred) {
            holder.star.setImageResource(R.drawable.ic_star_on_black_24dp)
        } else {
            holder.star.setImageResource(R.drawable.ic_star_off_black_24dp)
        }
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    fun setRepos(repos: List<Repo>) {
        this.repos.clear()
        this.repos.addAll(repos)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val star: ImageView = view.findViewById(R.id.star)
    }

}