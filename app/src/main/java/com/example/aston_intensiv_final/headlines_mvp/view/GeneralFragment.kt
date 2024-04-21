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
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
//import com.example.aston_intensiv_final.Retrofit.NewsApiClient
import com.example.aston_intensiv_final.SingleNewsViewModel
import com.example.aston_intensiv_final.SingletonNews
import com.example.aston_intensiv_final.headlines_mvp.presenter.HeadlinesPresenter
import moxy.MvpAppCompatFragment
//import moxy.MvpAppCompatFragment
//import moxy.MvpFragment
import moxy.ktx.moxyPresenter


class GeneralFragment : MvpAppCompatFragment(), ProfileView {
    //class HeadlinesFragment : Fragment() {
    //private lateinit var recycler: RecyclerView
    private lateinit var adapter: NewsAdapter
    val viewModel: SingleNewsViewModel by viewModels()

    //private val singleNewsModel: SingleNewsViewModel by activityViewModels()

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
        // val recyclerGeneral: RecyclerView = view.findViewById(R.id.recyclerGeneral)
        // recyclerGeneral.adapter = adapter
        // adapter.submitList(news)


        recyclerHeadlines = view.findViewById<RecyclerView>(R.id.recyclerGeneral)
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerGeneral)
        recyclerHeadlines.layoutManager = LinearLayoutManager(activity)

        //Toast.makeText(activity, "savedInstanceState= $savedInstanceState", Toast.LENGTH_SHORT).show()


        presenter.requestDataFromServer("general")



        return view
    }

    override fun addAdapter(list: List<NewsModel>) {
        //recyclerHeadlines.adapter = NewsAdapter(list, R.layout.news_item)
        adapter = NewsAdapter{ model ->
            clickedPosition = adapter.clickedPosition
            Toast.makeText(activity, "clickedPosition = $clickedPosition", Toast.LENGTH_SHORT).show()
            //singleNewsModel.singleNewsItems.value = list[clickedPosition].description
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainerView, HeadlinesSingleNewsFragment.newInstance()).commit()

           // viewModel.rollDice(list[clickedPosition].title,list[clickedPosition].description)

/*            binding.contentTV.text = it.description.toString()
            binding.headlineTV.text = it.title.toString()*/
            SingletonNews.addProduct(list[clickedPosition])

            //clickList()
        }
        recyclerHeadlines.adapter =adapter
        adapter.submitList(list.toMutableList())
    }

    companion object {
        fun newInstance() = GeneralFragment()

    }


}


/*private fun <VIEW : View> AppCompatActivity.find(@IdRes idRes: Int): Lazy<VIEW> {
    return lazy { findViewById(idRes) }
}*/

