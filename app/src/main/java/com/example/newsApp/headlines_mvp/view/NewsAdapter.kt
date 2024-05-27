package com.example.newsApp.headlines_mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsApp.Logo
import com.example.newsApp.NewsDiffUtil
import com.example.newsApp.R
import com.example.newsApp.databinding.NewsItemBinding
import com.example.newsApp.headlines_mvp.model.NewsModel

class NewsAdapter(
    private val onClickAction: (NewsModel) -> Unit,
) : ListAdapter<NewsModel, NewsAdapter.NewsViewHolder>
    (
    AsyncDifferConfig.Builder(NewsDiffUtil).build()
) {
    var clickedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAdapter.NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        val holder = NewsViewHolder(binding)

        clickedPosition = holder.adapterPosition
        binding.root.setOnClickListener {

            clickedPosition = holder.adapterPosition
            val model = getItem(holder.adapterPosition)
            onClickAction(model)
        }
        return holder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)

    }

    class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NewsModel) {
            binding.titleTV.text = model.title
            binding.sourceTv.text = model.source.name

            if (model.urlToImage != null) binding.newsImageView.load(model.urlToImage)
            else binding.newsImageView.load(
                R.drawable.no_image
            )


            var numberSources = 0

            for (sourcesName in Logo.values()) {

                if (model.source.name == sourcesName.nameMedia) {

                    numberSources = sourcesName.number
                }
            }

            when (numberSources) {

                1 -> binding.logoImageView.load(R.drawable.al_jazeera_logo)

                2 -> binding.logoImageView.load(R.drawable.ansa_logo)

                3 -> binding.logoImageView.load(R.drawable.aftenposten_logo)

                4 -> binding.logoImageView.load(R.drawable.bloomberg_logo)

                5, 6 -> binding.logoImageView.load(R.drawable.cnn_logo)

                7, 8 -> binding.logoImageView.load(R.drawable.fox_logo)

                9, 10 -> binding.logoImageView.load(R.drawable.abc_logo)

                11 -> binding.logoImageView.load(R.drawable.wt_logo)

                else -> {
                    binding.logoImageView.load(R.drawable.vecteezy_global_news_icon_style_7436645)
                }

            }
        }
    }

}