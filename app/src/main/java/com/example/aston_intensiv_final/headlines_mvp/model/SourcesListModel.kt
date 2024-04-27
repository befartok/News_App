package com.example.aston_intensiv_final.headlines_mvp.model

import com.example.aston_intensiv_final.headlines_mvp.model.Retrofit.NewsApiInterface
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SourcesListModel {
    val TAG = "MyApp"

    val interceptor = HttpLoggingInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor).build()

    fun getAllSources(): Single<NewsResponse> {

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(client)
            .build()

        val apiClient: NewsApiInterface = retrofit.create(NewsApiInterface::class.java)

        val allSources = apiClient.getAllSources(API_KEY)

        return allSources

    }

    fun getSingleSources(source: String): Single<NewsResponse> {

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(client)
            .build()

        val apiClient: NewsApiInterface = retrofit.create(NewsApiInterface::class.java)


        val singleSources = apiClient.getSingleSources(source, API_KEY)

        return singleSources

    }

    companion object {
        private const val API_KEY = "6352c6df6404441b92c9cb79b657b902"
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

}

