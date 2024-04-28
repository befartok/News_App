package com.example.aston_intensiv_final.headlines_mvp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_intensiv_final.BaseFragment
import com.example.aston_intensiv_final.HeadlinesSingleNewsFragment
import com.example.aston_intensiv_final.Item
import com.example.aston_intensiv_final.MainDB
import com.example.aston_intensiv_final.R
import com.example.aston_intensiv_final.SingletonNews
import com.example.aston_intensiv_final.databinding.FragmentSavedBinding
import com.example.aston_intensiv_final.databinding.FragmentSourcesBinding
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
import com.example.aston_intensiv_final.headlines_mvp.presenter.HeadlinesPresenter
import com.example.aston_intensiv_final.headlines_mvp.presenter.SourcesPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class SavedFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var adapter: NewsAdapter

    private val presenter by moxyPresenter { HeadlinesPresenter() }

    private var clickedPosition: Int = -1

    private lateinit var binding: FragmentSavedBinding

    private val TAG = "MyApp"

    // TODO: доделать

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedBinding.inflate(inflater, container, false)

        // binding.recyclerSaved.layoutManager = LinearLayoutManager(activity)

        //presenter.requestSavedFromDB(activity)

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
                    //Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
                }

                R.id.sources -> {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.fragmentContainerView, SourcesFragment.newInstance()).commit()
                }

            }
            true
        }

        getSavedFromDB()


        return binding.root
    }

    // TODO:  заполнять ресайклер из БД
    private fun getSavedFromDB() {
        val db = activity?.let { MainDB.getDB(it.applicationContext) }
        this.activity?.let {
            db?.getDao()?.getAllItems()?.asLiveData()?.observe(it) { list ->
                binding.textView.text = ""
                list.forEach {
                    val text =
                        "id=${it.id}," +
                                " title = ${it.title},urlToImage = ${it.urlToImage},publishedAt=${
                                    it.publishedAt
                                }, sourceName = ${it.sourceName}" +
                                //",content = ${it.content}" +
                                ",url = ${it.url}\n"
                    binding.textView.append(text)
                }
            }
        }
    }


    override fun addAdapter(list: List<NewsModel>) {

        Log.i(TAG, "sources addAdapter = ")

        adapter = NewsAdapter { model ->
            clickedPosition = adapter.clickedPosition

            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, HeadlinesSingleNewsFragment.newInstance())
                .commit()

            SingletonNews.addNews(list[clickedPosition])
        }
        binding.recyclerSaved.adapter = adapter
        adapter.submitList(list.toMutableList())
    }

    companion object {
        fun newInstance() = SavedFragment()
    }
}