package com.senas.waterreminder.MainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.senas.waterreminder.Adapters.PageAdapter
import com.senas.waterreminder.R

class WaterFragment: Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_water, container, false)
        viewPager = view.findViewById(R.id.viewpager_main)
        tabLayout = view.findViewById(R.id.tabs_main)

        val fragmentAdapter = PageAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
}