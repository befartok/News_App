package com.example.newsApp

import androidx.lifecycle.ViewModel
import com.example.newsApp.headlines_mvp.model.NewsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class SingleNewsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SingletonNews.getNews())
    val uiState: StateFlow<NewsModel?> = _uiState.asStateFlow()

}

object SingletonNews {

    private var news: NewsModel? = null
    var sourceName: String = ""
    var questionText: String = ""
    fun addNews(_news: NewsModel) {
        news = _news
    }


    fun getNews(): NewsModel? {
        return news
    }

    fun sendSourceName(name: String) {

        sourceName = name
    }

    fun sendQuestion(question: String) {

        questionText = question
    }


}