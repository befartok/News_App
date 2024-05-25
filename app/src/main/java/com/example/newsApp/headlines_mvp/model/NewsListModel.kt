package com.example.newsApp.headlines_mvp.model

import com.example.newsApp.headlines_mvp.model.Retrofit.NewsApiInterface
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsListModel {
    val TAG = "MyApp"

    private val interceptor = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor).build()

    fun getAllNews(category: String): Single<NewsResponse> {

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(client)
            .build()

        val apiClient: NewsApiInterface = retrofit.create(NewsApiInterface::class.java)


        val allNews = apiClient.getNews(category, API_KEY)

        return allNews

    }

    fun getSearchNews(question: String): Single<NewsResponse> {

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(client)
            .build()

        val apiClient: NewsApiInterface = retrofit.create(NewsApiInterface::class.java)


        val allSearch = apiClient.getSearch(question, API_KEY)

        return allSearch

    }


    companion object {
        private const val API_KEY = "6352c6df6404441b92c9cb79b657b902"
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

}

