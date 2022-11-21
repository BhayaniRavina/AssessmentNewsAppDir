package com.example.assessmentnewstest.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assessmentnewstest.databinding.ItemNewsBinding
import com.example.assessmentnewstest.models.NewsResponseItem
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(
    private var itemsList: ArrayList<NewsResponseItem>,
    fullItems: ArrayList<NewsResponseItem>
) :
    RecyclerView.Adapter<NewsAdapter.NewsItemsViewHolder>(), Filterable {
    inner class NewsItemsViewHolder(val viewDataBinding: ItemNewsBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemsViewHolder, position: Int) {
        val newsItem = itemsList[position]
        holder.viewDataBinding.apply {
            Glide.with(holder.viewDataBinding.ivNewsItem.context).load(newsItem.images?.square140).into(ivNewsItem)
            tvNewsTitle.text = newsItem.title
            tvNewsCategoryType.text = newsItem.type
            tvNewsPublishDate.text = newsItem.readablePublishedAt

            holder.viewDataBinding.cardView.setOnClickListener {
                onItemClickListener?.let {
                    it(newsItem)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return  itemsList.size
    }

    private var onItemClickListener: ((NewsResponseItem) -> Unit)? = null

    override fun getFilter(): Filter {
        return selectedCatogorysList
    }

    private val selectedCatogorysList: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<NewsResponseItem> = ArrayList()
            if (constraint == null || constraint.length == 0 || constraint.toString().lowercase().contains("all")) {
                filteredList.addAll(fullItems)
            } else {
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in fullItems) {
                    if (item.type?.lowercase()?.contains(filterPattern) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            itemsList.clear()
            itemsList.addAll(results.values as List<NewsResponseItem>)
            notifyDataSetChanged()
        }
    }
}