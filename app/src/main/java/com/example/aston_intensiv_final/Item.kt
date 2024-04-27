package com.example.aston_intensiv_final

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "price")
    var price: String
)*/
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,
    @ColumnInfo(name = "sourceName")
    var sourceName: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "url")
    var url: String
)



/*


binding.collapsingToolbar.title = it?.title.toString()
binding.imageViewSingleNews.load(it?.urlToImage)
binding.dateTV.text = it?.publishedAt.toString()
binding.sourceTV.text = it?.source?.name.toString()
binding.contentTV.text
url*/
