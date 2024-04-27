package com.example.aston_intensiv_final

import androidx.recyclerview.widget.DiffUtil
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel


object NewsDiffUtil : DiffUtil.ItemCallback<NewsModel>() {

    override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
        return oldItem == newItem
    }

}