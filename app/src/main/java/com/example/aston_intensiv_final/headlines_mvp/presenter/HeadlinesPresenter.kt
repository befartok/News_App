package com.example.aston_intensiv_final.headlines_mvp.presenter

import android.util.Log
import com.example.aston_intensiv_final.headlines_mvp.model.NewsListModel
import com.example.aston_intensiv_final.headlines_mvp.view.ProfileView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class HeadlinesPresenter : MvpPresenter<ProfileView>() {
    val TAG = "MyApp"

    private val newsListModel: NewsListModel = NewsListModel()


    fun requestDataFromServer(category: String): Disposable {


        val requestAllNews = newsListModel.getAllNews(category)

        Log.i(TAG, "requestAllNews = newsListModel.getAllNews(category) = $requestAllNews");

        return requestAllNews
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val allNews = it.articles
                    viewState.addAdapter(allNews)
                },
                { error ->
                    Log.e(TAG, error.toString())
                }
            )

    }

    fun requestSearchFromServer(question: String): Disposable {


        val requestSearchNews = newsListModel.getSearchNews(question)

        Log.i(TAG, "requestSearchNews = newsListModel.getSearchNews(question)= $requestSearchNews");

        return requestSearchNews
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val allNews = it.articles
                    viewState.addAdapter(allNews)
                },
                { error ->
                    Log.e(TAG, error.toString())
                }
            )

    }


    fun requestDataFromCache() {
        //todo

    }

}

