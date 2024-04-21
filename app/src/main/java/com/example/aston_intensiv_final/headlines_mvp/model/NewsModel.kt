package com.example.aston_intensiv_final.headlines_mvp.model

import java.util.Date

data class NewsModel(
    val id: Int,
    //val author: String,
    val urlToImage: String,
    val title: String,
    val publishedAt: Date,
    val source: SourceClass,
    var content: String,
    var url: String

)

