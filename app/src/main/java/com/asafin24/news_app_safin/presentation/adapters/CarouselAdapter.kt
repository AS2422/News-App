package com.asafin24.news_app_safin.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asafin24.domain.models.Article
import com.asafin24.news_app_safin.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.carousel_item.view.*

class CarouselAdapter : RecyclerView.Adapter<BannerVH>() {

    private var carouslelList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerVH =
        BannerVH(LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false))

    override fun getItemCount(): Int = carouslelList.size

    override fun onBindViewHolder(holder: BannerVH, position: Int): Unit = holder.itemView.run {
        holder.itemView.tv_title_carousel.text = carouslelList[position].title
        Glide.with(this).load(carouslelList[position].urlToImage).into(iv_carousel)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Article>) {
        carouslelList = list
        notifyDataSetChanged()
    }

}

class BannerVH(itemView: View) : RecyclerView.ViewHolder(itemView)