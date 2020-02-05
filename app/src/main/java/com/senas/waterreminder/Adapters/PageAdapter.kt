package com.senas.waterreminder.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.senas.waterreminder.MainFragments.SettingsFragment
import com.senas.waterreminder.MainFragments.StatisticFragment
import com.senas.waterreminder.MainFragments.WaterFragment
import com.senas.waterreminder.WaterFragments.TabCoffeeFragment
import com.senas.waterreminder.WaterFragments.TabWaterFragment

class PageAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TabWaterFragment()
            }

            else -> {
                return TabCoffeeFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Water"
            else -> {
                return "Cofee"
            }
        }
    }
}