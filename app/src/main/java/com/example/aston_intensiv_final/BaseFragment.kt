package com.example.aston_intensiv_final

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.aston_intensiv_final.databinding.FragmentBaseBinding
import com.example.aston_intensiv_final.headlines_mvp.view.BusinessFragment
import com.example.aston_intensiv_final.headlines_mvp.view.GeneralFragment
import com.example.aston_intensiv_final.headlines_mvp.view.SportFragment
import com.google.android.material.tabs.TabLayout

class BaseFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding
    private val fragmentList = listOf(
        GeneralFragment.newInstance(), BusinessFragment.newInstance(), SportFragment.newInstance()
    )

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)



        /*        binding.buttonToGoFragmentC.setOnClickListener {
                  parentFragmentManager.beginTransaction().addToBackStack("attachB")
                      .replace(R.id.fragment_container, FragmentC.newInstance("Hello Fragment C"))
                      .commit()
              }
              binding.buttonBack.setOnClickListener {
                  parentFragmentManager.popBackStack()
              }*/
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.headlines -> {
                    Toast.makeText(activity, "headlines", Toast.LENGTH_SHORT).show()
                }

                R.id.saved -> {
                    Toast.makeText(activity, "saved", Toast.LENGTH_SHORT).show()
                }

                R.id.sources -> {
                    Toast.makeText(activity, "sources", Toast.LENGTH_SHORT).show()

                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.placeholder, SoursesFragment.newInstance()).commit()
                }
            }
            true
        }

        binding.toolbar.apply {

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
        }

        binding.tabLayoutHeadlines.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab != null) {
                    parentFragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.placeholder, fragmentList[tab.position] as Fragment).commit()
                }
                if (tab != null) {
                    Toast.makeText(activity, "Tab selected: ${tab.position}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        parentFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.placeholder, GeneralFragment.newInstance()).commit()

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
        fun newInstance() = BaseFragment()
    }
}