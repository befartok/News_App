package com.example.newsApp.headlines_mvp.view

import com.example.newsApp.headlines_mvp.model.NewsModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addAdapter(list: List<NewsModel>)

}