package com.example.aston_intensiv_final.headlines_mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensiv_final.HeadlinesSingleNewsFragment
import com.example.aston_intensiv_final.R
import com.example.aston_intensiv_final.SingleNewsViewModel
import com.example.aston_intensiv_final.SingletonNews
import com.example.aston_intensiv_final.databinding.FragmentHeadlinesSingleNewsBinding
import com.example.aston_intensiv_final.databinding.FragmentSearchBinding
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
import com.example.aston_intensiv_final.headlines_mvp.presenter.HeadlinesPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SearchFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var adapter: NewsAdapter

    private val presenter by moxyPresenter { HeadlinesPresenter() }

    private var clickedPosition: Int = -1
    private lateinit var binding: FragmentSearchBinding

    private val TAG = "MyApp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.navigationBackSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.cancelSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.recyclerSearch.layoutManager = LinearLayoutManager(activity)



        presenter.requestSearchFromServer(SingletonNews.questionText)

        binding.toolbar.title = SingletonNews.questionText

        return binding.root
    }

    override fun addAdapter(list: List<NewsModel>) {
        Log.i(TAG, "news addAdapter = ");

        adapter = NewsAdapter {
            clickedPosition = adapter.clickedPosition

            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, HeadlinesSingleNewsFragment.newInstance())
                .commit()

            SingletonNews.addNews(list[clickedPosition])

        }
        binding.recyclerSearch.adapter = adapter
        adapter.submitList(list.toMutableList())
    }


    companion object {
        fun newInstance() = SearchFragment()

    }
}