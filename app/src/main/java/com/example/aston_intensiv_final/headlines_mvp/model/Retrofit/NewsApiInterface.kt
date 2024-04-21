package com.example.aston_intensiv_final.headlines_mvp.model.Retrofit

import com.example.aston_intensiv_final.headlines_mvp.model.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {
   //@GET("everything?q=keyword")
   @GET("top-headlines?country=us")
  // @GET("top-headlines?country=us&category=sports")
    fun getNews(
       @Query("category") category: String,
       @Query("apikey") apiKey: String,
    ): Single<NewsResponse>
}

//https://newsapi.org/v2/top-headlines?country=ru&category=general&apiKey=6352c6df6404441b92c9cb79b657b902