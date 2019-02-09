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
    fun onStarClick(repo: Repo)
  }

  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
    val inflate = LayoutInflater.from(parent.context)
    return ViewHolder(inflate.inflate(R.layout.item_repo, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val repo = repos[position]
    holder.name.text = repo.nameWithOwner
    holder.description.text = repo.description
    holder.starCount.text = repo.stargazersTotalCount.toString()
    holder.language.text = repo.primaryLanguage
    holder.star.setOnClickListener { listener?.onStarClick(repo) }
    if (repo.hasStarred) {
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
    val description: TextView = view.findViewById(R.id.description)
    val starCount: TextView = view.findViewById(R.id.star_count)
    val language: TextView = view.findViewById(R.id.language)
    val star: ImageView = view.findViewById(R.id.star)
  }

}