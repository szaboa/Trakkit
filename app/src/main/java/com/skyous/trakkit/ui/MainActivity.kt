package com.skyous.trakkit.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.skyous.trakkit.R
import com.skyous.trakkit.TrakkitApplication
import com.skyous.trakkit.ui.navigation.core.BackNavigationListener
import com.skyous.trakkit.ui.navigation.core.MainNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BackNavigationListener {

    private val mainNavigator: MainNavigator = MainNavigator.instance

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        mainNavigator.navigateTo(item.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainNavigator.setHost(this, savedInstanceState)
        TrakkitApplication.getComponent().inject(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onBackPressed() {
        if (mainNavigator.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        mainNavigator.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        mainNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigatedBack(tab: MainNavigator.Tab) {
        navigation.selectedItemId = tab.itemResId
        super.onNavigatedBack(tab)
    }
}
