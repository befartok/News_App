package com.example.aston_intensiv_final.headlines_mvp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aston_intensiv_final.ContactDiffUtil
import com.example.aston_intensiv_final.NewsAdapterOld
import com.example.aston_intensiv_final.NewsDiffUtil
import com.example.aston_intensiv_final.NewsModelOld
import com.example.aston_intensiv_final.R
import com.example.aston_intensiv_final.databinding.NewsItemBinding
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel

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
        //val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        //return NewsViewHolder(view)
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
        //val current = newsList[position]
        val model = getItem(position)
        holder.bind(model)

/*        holder.newsImageView.load(newsList[position].urlToImage)

        holder.newsDescription.text = current.description
        holder.newsSource.text = current.source.name*/
    }

/*    override fun getItemCount(): Int {
        return newsList.size
    }*/

    // class NewsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
/*        RecyclerView.ViewHolder(binding.root) {
        internal var newsDescription: TextView = v.findViewById(R.id.description)
        internal var newsImageView: ImageView = v.findViewById(R.id.newsImageView)
        internal var newsSource: TextView = v.findViewById(R.id.sourceTv)
    }*/
    fun bind(model: NewsModel) {
    binding.titleTV.text = model.title
    binding.sourceTv.text = model.source.name
    binding.newsImageView.load(model.urlToImage)

    }
    }

}