package com.example.assessmentnewstest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentnewstest.databinding.ItemFilterBinding
import com.example.assessmentnewstest.models.FilterCategories

class NewsFilterAdapter :
    RecyclerView.Adapter<NewsFilterAdapter.NewsFilterItemsViewHolder>() {
    inner class NewsFilterItemsViewHolder(val viewItemFilterBinding: ItemFilterBinding) : RecyclerView.ViewHolder(viewItemFilterBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<FilterCategories>(){
        override fun areItemsTheSame(oldItem: FilterCategories, newItem: FilterCategories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FilterCategories, newItem: FilterCategories): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFilterItemsViewHolder {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsFilterItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsFilterItemsViewHolder, position: Int) {
        val itemOfCategories = differ.currentList[position]
        holder.viewItemFilterBinding.apply {
            tvNewsFilter.text = itemOfCategories.category
            cardViewFilter.setOnClickListener {

                //cardViewFilter.setCardBackgroundColor(Color.parseColor("#934086"))
                onItemClickListener?.let { it(itemOfCategories) }
            }
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    private var onItemClickListener: ((FilterCategories) -> Unit)? = null

    fun setOnItemClickListener(listener: (FilterCategories) -> Unit) {
        onItemClickListener = listener
    }
}