package com.example.aston_intensiv_final.headlines_mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aston_intensiv_final.Logo
import com.example.aston_intensiv_final.NewsDiffUtil
import com.example.aston_intensiv_final.R
import com.example.aston_intensiv_final.databinding.SourcesItemBinding
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel

class SourcesAdapter(
    private val onClickAction: (NewsModel) -> Unit,
) : ListAdapter<NewsModel, SourcesAdapter.SourcesViewHolder>
    (
    AsyncDifferConfig.Builder(NewsDiffUtil).build()
) {
    var clickedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SourcesAdapter.SourcesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SourcesItemBinding.inflate(inflater, parent, false)
        val holder = SourcesViewHolder(binding)
        //todo убрать деприкейтед
        clickedPosition = holder.adapterPosition
        binding.root.setOnClickListener {

            clickedPosition = holder.adapterPosition
            val model = getItem(holder.adapterPosition)
            onClickAction(model)
        }
        return holder
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)

    }
    class SourcesViewHolder(private val binding: SourcesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NewsModel) {
            val TAG = "MyApp"
            binding.sourceTV.text = model.name
            binding.categoryTV.text = model.category
            binding.country.text = model.country

            var numberSources: Int = 0

            for(sourcesName in Logo.values()){

                if (model.name == sourcesName.nameMedia) {

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




