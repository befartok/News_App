package com.example.aston_intensiv_final

import android.appwidget.AppWidgetHost
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
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
import coil.load
import com.example.aston_intensiv_final.databinding.FragmentHeadlinesSingleNewsBinding
import kotlinx.coroutines.launch

class HeadlinesSingleNewsFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesSingleNewsBinding

    // private val singleNewsModel: SingleNewsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesSingleNewsBinding.inflate(inflater, container, false)


        /*        binding.buttonToGoFragmentC.setOnClickListener {
                  parentFragmentManager.beginTransaction().addToBackStack("attachB")
                      .replace(R.id.fragment_container, FragmentC.newInstance("Hello Fragment C"))
                      .commit()
              }
              binding.buttonBack.setOnClickListener {
                  parentFragmentManager.popBackStack()
              }*/


        /*       binding.toolbarSingleNews.apply {

                   inflateMenu(R.menu.main_menu)
                   setOnMenuItemClickListener() {

                       when (it.itemId) {
                           R.id.searchItem -> {
                               Toast.makeText(activity, "searchItem", Toast.LENGTH_SHORT).show()
                           }

                           R.id.filterItem -> {
                               Toast.makeText(activity, "filterItem", Toast.LENGTH_SHORT).show()
                           }
                       }
                       true
                   }
               }*/
        /*        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
                binding.collapsingToolbar.title = "Your Title"*/

        //binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.navigationBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.savedSmall.setOnClickListener {
            // TODO: add saved
            Toast.makeText(activity, "TODO: add saved ", Toast.LENGTH_SHORT).show()
        }
        //binding.collapsingToolbar.setNavigationIcon(R.drawable.ic_back)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(binding.toolbar)

        // binding.collapsingToolbar.title = "Your Title"
        // val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsingToolbar)


        /*        singleNewsModel.singleNewsItems.observe(activity as LifecycleOwner, {
                    binding.contentTV.text = it
                } )
                */
        /*        fun makeLinks(
                    text: String,
                    phrase: String,
                    phraseColor: Int,
                    listener: View.OnClickListener
                ): SpannableString {
                    val spannableString = SpannableString(text)
                    val clickableSpan = object : ClickableSpan() {
                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = phraseColor // you can use custom color
                            ds.isUnderlineText = false // this remove the underline
                        }

                        override fun onClick(view: View) {
                            listener.onClick(view)
                        }
                    }
                    val start = text.indexOf(phrase)
                    val end = start + phrase.length
                    spannableString.setSpan(
                        clickableSpan,
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    return spannableString
                }*/

        val viewModel: SingleNewsViewModel by viewModels()
        // viewModel.rollDice()


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.collapsingToolbar.title = it?.title.toString()
                    binding.imageViewSingleNews.load(it?.urlToImage)
                    binding.headlineTV.text = it?.title.toString()
                    binding.dateTV.text = it?.publishedAt.toString()
                    binding.sourceTV.text = it?.source?.name.toString()
                    //binding.contentTV.text = it?.content?.toString()

                    // val spannableTxt = binding.spannableTxt

                    val strSpannable = it?.content?.toSpannable() as SpannableString
                    strSpannable.withClicableSpan(
                        "${it.content?.toString()}",
                        Color.BLUE,
                        true,
                        onClickListener = {
                            val termUrl = Uri.parse("${it.url}")
                            startActivity(Intent(Intent.ACTION_VIEW, termUrl))
                        }
                    )

                    binding.contentTV.text = strSpannable
                    binding.contentTV.movementMethod = LinkMovementMethod.getInstance()
                    binding.contentTV.highlightColor = Color.TRANSPARENT

                }
            }
        }


        //binding.toolbar.OnMenuItemClickListener()

        /*
                binding.toolbar.setOnMenuItemClickListener() {
                    when (it.itemId) {
                        R.id.searchItem -> {}
                        R.id.filterItem -> {}
                    }
                    true
                }
        */
        return binding.root
    }

    companion object {
        fun newInstance() = HeadlinesSingleNewsFragment()
    }
}