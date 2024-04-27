package com.example.aston_intensiv_final.headlines_mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensiv_final.HeadlinesSingleNewsFragment
import com.example.aston_intensiv_final.R
import com.example.aston_intensiv_final.SingleNewsViewModel
import com.example.aston_intensiv_final.SingletonNews
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
import com.example.aston_intensiv_final.headlines_mvp.presenter.HeadlinesPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SportFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var adapter: NewsAdapter
    val viewModel: SingleNewsViewModel by viewModels()

    private val presenter by moxyPresenter { HeadlinesPresenter() }

    lateinit var recyclerHeadlines: RecyclerView
    private var clickedPosition: Int = -1
    private var news = mutableListOf<NewsModel>()

    private val TAG = "MyApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_headlines, container, false)

        recyclerHeadlines = view.findViewById<RecyclerView>(R.id.recyclerGeneral)
        recyclerHeadlines.layoutManager = LinearLayoutManager(activity)

        presenter.requestDataFromServer("sports")

        return view
    }

    override fun addAdapter(list: List<NewsModel>) {
        adapter = NewsAdapter{ model ->
            clickedPosition = adapter.clickedPosition
            Toast.makeText(activity, "clickedPosition = $clickedPosition", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, HeadlinesSingleNewsFragment.newInstance()).commit()

            SingletonNews.addNews(list[clickedPosition])
        }
        recyclerHeadlines.adapter =adapter
        adapter.submitList(list.toMutableList())
    }

    companion object {
        fun newInstance() = SportFragment()
    }
}