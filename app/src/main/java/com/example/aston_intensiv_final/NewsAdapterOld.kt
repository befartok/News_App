package com.example.aston_intensiv_final

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.aston_intensiv_final.databinding.NewsItemOldBinding

class NewsAdapterOld(
    private val onClickAction: (NewsModelOld) -> Unit,
) : ListAdapter<NewsModelOld, NewsAdapterOld.NewsViewHolderOld>(
    AsyncDifferConfig
        .Builder(ContactDiffUtil)
        .build()
)

{
    var clickedPosition = -1
    var listPosToDel: MutableList<Int> = mutableListOf<Int>()
    var isStartDel: Boolean = false
    var selects = Array(150) { false }.toBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolderOld {

        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemOldBinding.inflate(inflater, parent, false)
        val holder = NewsViewHolderOld(binding)

        clickedPosition = holder.adapterPosition

        binding.root.setOnClickListener {

            clickedPosition = holder.adapterPosition
            val model = getItem(holder.adapterPosition)
            onClickAction(model)

            if (isStartDel) {
                if (!model.isSelected) {
                    model.isSelected = true
                    selects[clickedPosition] = true
                    listPosToDel.add(clickedPosition)
                    listPosToDel.sort()
                } else {
                    model.isSelected = false
                    selects[clickedPosition] = false
                    listPosToDel = listPosToDel.filter { it != clickedPosition }.toMutableList()
                    listPosToDel.sort()
                }
                notifyDataSetChanged()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: NewsViewHolderOld, position: Int) {
        val model = getItem(position)
        holder.bind(model)

        if (selects[position]) {
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    class NewsViewHolderOld(private val binding: NewsItemOldBinding) : ViewHolder(binding.root) {
        fun bind(model: NewsModelOld) {
            binding.thumbnail.setImageResource(R.drawable.thumbnail)
            binding.monogram.setImageResource(R.drawable.monogram)
            binding.logoName.text = model.logoText
            binding.headlinesTextView.text = model.headlinesText
        }
    }

}

