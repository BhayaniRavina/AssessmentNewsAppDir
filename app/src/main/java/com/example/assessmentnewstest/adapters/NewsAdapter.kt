package com.example.assessmentnewstest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assessmentnewstest.databinding.ItemNewsBinding
import com.example.assessmentnewstest.models.NewsResponseItem

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsItemsViewHolder>() {
    inner class NewsItemsViewHolder(val viewDataBinding: ItemNewsBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<NewsResponseItem>(){
        override fun areItemsTheSame(oldItem: NewsResponseItem, newItem: NewsResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsResponseItem, newItem: NewsResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemsViewHolder, position: Int) {
        val newsItem = differ.currentList[position]
        holder.viewDataBinding.apply {
            Glide.with(holder.viewDataBinding.ivNewsItem.context).load(newsItem.images?.square140).into(ivNewsItem)
            tvNewsTitle.text = newsItem?.title
            tvNewsCategoryType.text = newsItem?.type
            tvNewsPublishDate.text = newsItem?.readablePublishedAt
            //tvNewsPublishDate.text = newsItem?.publishedAt?.let { convertLongToTime(it) }

            holder.viewDataBinding.cardView.setOnClickListener {
                onItemClickListener?.let { it(newsItem) }
            }
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    private var onItemClickListener: ((NewsResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}