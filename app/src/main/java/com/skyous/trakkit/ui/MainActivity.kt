package com.skyous.trakkit.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.skyous.trakkit.R
import com.skyous.trakkit.TrakkitApplication
import com.skyous.trakkit.ui.navigation.core.BackNavigationListener
import com.skyous.trakkit.ui.navigation.core.MainNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),BackNavigationListener {

    private val mainNavigator: MainNavigator = MainNavigator.instance

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mainNavigator.navigateTo(MainNavigator.Tab.HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_library -> {
                mainNavigator.navigateTo(MainNavigator.Tab.LIBRARY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_browse -> {
                mainNavigator.navigateTo(MainNavigator.Tab.BROWSE)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                mainNavigator.navigateTo(MainNavigator.Tab.PROFILE)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainNavigator.setHost(this)
        TrakkitApplication.getComponent().inject(this)
        mainNavigator.navigateTo(MainNavigator.Tab.HOME)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    override fun onBackPressed() {
        if (mainNavigator.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    override fun onNavigatedBack(tab: MainNavigator.Tab) {
        navigation.selectedItemId = tab.itemResId
        super.onNavigatedBack(tab)
    }
}
