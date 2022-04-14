package com.asafin24.news_app_safin.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asafin24.domain.models.Article
import com.asafin24.news_app_safin.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ProductVH>() {

    private var newsList = emptyList<Article>()

    class ProductVH(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH =
        ProductVH(LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false))

    override fun onBindViewHolder(holder: ProductVH, position: Int): Unit = holder.itemView.run {

        holder.itemView.tv_title_news.text = newsList[position].title
        holder.itemView.tv_author.text = newsList[position].author

        if (newsList[position].urlToImage.isNullOrEmpty()) Glide.with(this).load(R.drawable.ic_main_icon).into(iv_news)
        else Glide.with(this).load(newsList[position].urlToImage).into(iv_news)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Article>) {
        newsList = list
        notifyDataSetChanged()
    }

}