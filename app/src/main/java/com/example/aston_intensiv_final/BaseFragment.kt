package com.example.aston_intensiv_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import com.example.aston_intensiv_final.databinding.FragmentBaseBinding
import com.example.aston_intensiv_final.headlines_mvp.view.BusinessFragment
import com.example.aston_intensiv_final.headlines_mvp.view.GeneralFragment
import com.example.aston_intensiv_final.headlines_mvp.view.SavedFragment
import com.example.aston_intensiv_final.headlines_mvp.view.SearchFragment
import com.example.aston_intensiv_final.headlines_mvp.view.SourcesFragment
import com.example.aston_intensiv_final.headlines_mvp.view.SportFragment
import com.google.android.material.tabs.TabLayout

class BaseFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding
    private val fragmentList = listOf(
        GeneralFragment.newInstance(), BusinessFragment.newInstance(), SportFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.saved -> {
                    Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
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
        binding.searchBar.isGone = true
        binding.toolbar.apply {

            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener() {

                when (it.itemId) {
                    R.id.searchItem -> {

                        binding.searchBar.isGone = false

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

            Toast.makeText(activity, "cancelIV", Toast.LENGTH_SHORT).show()

            binding.searchBar.isGone = true

        }

        binding.tabLayoutHeadlines.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab != null) {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.placeholder, fragmentList[tab.position] as Fragment).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        parentFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.placeholder, GeneralFragment.newInstance()).commit()

        return binding.root
    }

    companion object {
        fun newInstance() = BaseFragment()
    }
}