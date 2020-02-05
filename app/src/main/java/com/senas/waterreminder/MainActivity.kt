package com.senas.waterreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.senas.waterreminder.MainFragments.SettingsFragment
import com.senas.waterreminder.MainFragments.StatisticFragment
import com.senas.waterreminder.MainFragments.WaterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.water -> {
                val fragment = WaterFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.statistic -> {
                val fragment = StatisticFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.settings -> {
                val fragment = SettingsFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = WaterFragment()
        addFragment(fragment)
    }
}
