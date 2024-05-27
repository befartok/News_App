package com.example.newsApp

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
import androidx.core.text.toSpannable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.newsApp.databinding.FragmentHeadlinesSingleNewsBinding
import com.example.newsApp.headlines_mvp.view.SourcesFragment
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