package com.example.aston_intensiv_final.headlines_mvp.model.Retrofit

import com.example.aston_intensiv_final.headlines_mvp.model.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {
   @GET("top-headlines?country=us")
    fun getNews(
       @Query("category") category: String,
       @Query("apikey") apiKey: String
    ): Single<NewsResponse>

    @GET("top-headlines/sources?")
    fun getAllSources(
        @Query("apikey") apiKey: String
    ): Single<NewsResponse>

    @GET("top-headlines")
    fun getSingleSources(
        @Query("sources") sources: String,
        @Query("apikey") apiKey: String
    ): Single<NewsResponse>

    @GET("everything")
    fun getSearch(
        @Query("q") sources: String,
        @Query("apikey") apiKey: String
    ): Single<NewsResponse>
}
