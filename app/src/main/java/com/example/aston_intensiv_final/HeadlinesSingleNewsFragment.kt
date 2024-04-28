package com.example.aston_intensiv_final

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import coil.load
import com.example.aston_intensiv_final.databinding.FragmentHeadlinesSingleNewsBinding
import com.example.aston_intensiv_final.headlines_mvp.view.SavedFragment
import com.example.aston_intensiv_final.headlines_mvp.view.SourcesFragment
import kotlinx.coroutines.launch

class HeadlinesSingleNewsFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesSingleNewsBinding
    private var urlNews: String = ""
    private var urlImage: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesSingleNewsBinding.inflate(inflater, container, false)

        binding.navigationBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.savedSmall.setOnClickListener {

            val db = activity?.let { MainDB.getDB(it.applicationContext) }

            val item = Item(
                null,
                binding.headlineTV.text.toString(),
                urlImage,
                binding.dateTV.text.toString(),
                binding.sourceTV.text.toString(),
                binding.contentTV.text.toString(),
                urlNews
            )
            Thread {
                db?.getDao()?.insertItem(item)

            }.start()
            Toast.makeText(activity, "TODO: add saved ", Toast.LENGTH_SHORT).show()
        }

        val viewModel: SingleNewsViewModel by viewModels()



        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.collapsingToolbar.title = it?.title.toString()
                    binding.imageViewSingleNews.load(it?.urlToImage)
                    binding.headlineTV.text = it?.title.toString()
                    binding.dateTV.text = it?.publishedAt.toString()
                    binding.sourceTV.text = it?.source?.name.toString()

                    urlImage = it?.urlToImage.toString()
                    urlNews = it?.url.toString()

                    val strSpannable: SpannableString

                    if (it?.content != null
                    ) {
                        strSpannable = it.content.toSpannable() as SpannableString
                        strSpannable.withClicableSpan(
                            it.content,
                            Color.BLUE,
                            true,
                            onClickListener = {
                                val termUrl = Uri.parse(it.url)

                                startActivity(Intent(Intent.ACTION_VIEW, termUrl))
                            }
                        )
                        binding.contentTV.text = strSpannable
                        binding.contentTV.movementMethod = LinkMovementMethod.getInstance()
                        binding.contentTV.highlightColor = Color.TRANSPARENT
                    }
                }


            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.headlines -> {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.fragmentContainerView, BaseFragment.newInstance()).commit()
                }

                R.id.saved -> {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.fragmentContainerView, SavedFragment.newInstance()).commit()
                }

                R.id.sources -> {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.fragmentContainerView, SourcesFragment.newInstance()).commit()
                }
            }
            true
        }
        return binding.root
    }

    companion object {
        fun newInstance() = HeadlinesSingleNewsFragment()
    }
}