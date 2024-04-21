package com.example.aston_intensiv_final.headlines_mvp.view

import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addAdapter(list: List<NewsModel>)

}