package com.example.newsApp.headlines_mvp.model

data class NewsResponse(

    var status: Boolean,
    var totalResults: Int,
    var articles: List<NewsModel>,
    var sources: List<NewsModel>

    )