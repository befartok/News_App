package com.example.newsApp.headlines_mvp.presenter

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.newsApp.headlines_mvp.model.SourcesListModel
import com.example.newsApp.headlines_mvp.view.ProfileView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class SourcesPresenter : MvpPresenter<ProfileView>() {

    private val sourcesListModel: SourcesListModel = SourcesListModel()

    fun requestAllSourcesFromServer(): Disposable {

        val TAG = "MyApp"

        val requestAllSources = sourcesListModel.getAllSources()

        return requestAllSources
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val allSources = it.sources
                    Log.i(TAG, "it.sources = ${it.sources}")

                    viewState.addAdapter(allSources)
                },
                { error ->
                    Log.e(TAG, error.toString())
                }
            )
    }

    fun requestSingleSourcesFromServer(
        sourceName: String,
        activity: FragmentActivity?
    ): Disposable {

        val TAG = "MyApp"

        val requestSingleSources = sourcesListModel.getSingleSources(sourceName)


        return requestSingleSources
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val singleSources = it.articles
                    Log.i(TAG, "it.sources = ${it.articles}")
                    if (it.totalResults == 0) Toast.makeText(
                        activity,
                        "Отсутствует контент источника",
                        Toast.LENGTH_SHORT
                    ).show()

                    viewState.addAdapter(singleSources)
                },
                { error ->
                    Log.e(TAG, error.toString())
                }
            )

    }


}

