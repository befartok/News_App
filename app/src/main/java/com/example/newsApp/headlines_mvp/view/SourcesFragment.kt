package com.example.newsApp.headlines_mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsApp.BaseFragment
import com.example.newsApp.R
import com.example.newsApp.SingletonNews
import com.example.newsApp.databinding.FragmentSourcesBinding
import com.example.newsApp.headlines_mvp.model.NewsModel
import com.example.newsApp.headlines_mvp.presenter.SourcesPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SourcesFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var adapter: SourcesAdapter

    private val presenter by moxyPresenter { SourcesPresenter() }

    private var clickedPosition: Int = -1

    private lateinit var binding: FragmentSourcesBinding

    private val TAG = "MyApp"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSourcesBinding.inflate(inflater, container, false)

        binding.recyclerSources.layoutManager = LinearLayoutManager(activity)

        presenter.requestAllSourcesFromServer()

        binding.searchBar.isGone = true
        binding.toolbar.apply {

            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener() {

                when (it.itemId) {
                    R.id.searchItem -> {
                        binding.searchBar.isGone = false

                        binding.toolbar.title = ""

                    }

                }
                true
            }
        }

        binding.doneIV.setOnClickListener {
            SingletonNews.sendQuestion(binding.searchET.text.toString())
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, SearchFragment.newInstance()).commit()
        }

        binding.cancelIV.setOnClickListener {

            binding.searchBar.isGone = true
            binding.toolbar.title = "Sources"

        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.headlines -> {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.fragmentContainerView, BaseFragment.newInstance()).commit()
                }

            }
            true
        }


        return binding.root
    }

    override fun addAdapter(list: List<NewsModel>) {

        Log.i(TAG, "sources addAdapter = ")

        adapter = SourcesAdapter { model ->
            clickedPosition = adapter.clickedPosition

            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, SingleSourcesListFragment.newInstance())
                .commit()

            SingletonNews.sendSourceName(list[clickedPosition].name)
        }
        binding.recyclerSources.adapter = adapter
        adapter.submitList(list.toMutableList())
    }

    companion object {
        fun newInstance() = SourcesFragment()
    }
}