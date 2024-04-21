package com.example.aston_intensiv_final.headlines_mvp.model

import com.example.aston_intensiv_final.headlines_mvp.model.Retrofit.NewsApiInterface
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsListModel {

    fun getAllNews(category:String): Single<NewsResponse> {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val apiClient: NewsApiInterface = retrofit.create(NewsApiInterface::class.java)


        val allNews = apiClient.getNews(category, API_KEY)

        return allNews

    }
    companion object {
        //private const val CATEGORY="general"
        //private const val CATEGORY="business"
        private const val API_KEY = "6352c6df6404441b92c9cb79b657b902"
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

}

