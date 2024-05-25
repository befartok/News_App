package com.example.newsApp.headlines_mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsApp.HeadlinesSingleNewsFragment
import com.example.newsApp.R
import com.example.newsApp.headlines_mvp.model.NewsModel
//import com.example.aston_intensiv_final.Retrofit.NewsApiClient
import com.example.newsApp.SingletonNews
import com.example.newsApp.databinding.FragmentHeadlinesBinding
import com.example.newsApp.headlines_mvp.presenter.HeadlinesPresenter
import moxy.MvpAppCompatFragment
//import moxy.MvpAppCompatFragment
//import moxy.MvpFragment
import moxy.ktx.moxyPresenter


class GeneralFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var adapter: NewsAdapter


    private val presenter by moxyPresenter { HeadlinesPresenter() }

    private var clickedPosition: Int = -1

    private lateinit var binding: FragmentHeadlinesBinding

    private val TAG = "MyApp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)

        binding.recyclerGeneral.layoutManager = LinearLayoutManager(activity)

        presenter.requestDataFromServer("general")

        return binding.root

    }

    override fun addAdapter(list: List<NewsModel>) {
        Log.i(TAG, "news addAdapter = ")

        adapter = NewsAdapter { model ->
            clickedPosition = adapter.clickedPosition

            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, HeadlinesSingleNewsFragment.newInstance())
                .commit()

            SingletonNews.addNews(list[clickedPosition])
        }
        binding.recyclerGeneral.adapter = adapter
        adapter.submitList(list.toMutableList())
    }

    companion object {
        fun newInstance() = GeneralFragment()

    }
}

