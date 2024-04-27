package com.example.aston_intensiv_final.headlines_mvp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_intensiv_final.BaseFragment
import com.example.aston_intensiv_final.R
import com.example.aston_intensiv_final.SingletonNews
import com.example.aston_intensiv_final.databinding.FragmentSourcesBinding
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
import com.example.aston_intensiv_final.headlines_mvp.presenter.SourcesPresenter
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

                    R.id.filterItem -> {
                        Toast.makeText(activity, "filterItem", Toast.LENGTH_SHORT).show()
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

                R.id.saved -> {
                    Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
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