package com.example.newsApp.headlines_mvp.model

import java.util.Date

data class NewsModel(
    val id: String,
    val urlToImage: String,
    val title: String,
    val publishedAt: Date,
    val source: SourceClass,
    var content: String,
    var url: String,
    var name: String,
    var description: String,
    var category: String,
    var country: String

)

