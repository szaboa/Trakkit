package com.skyous.trakkit.ui.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.skyous.trakkit.R
import java.lang.ref.WeakReference

/**
 * Class comment here
 *
 * @author Peter Abraham
 * @since 10/18/2018
 *
 */

class MainNavigator private constructor() {

    private object Holder {
        val INSTANCE = MainNavigator()
    }

    private lateinit var activity: WeakReference<FragmentActivity>

    /*home library browse profile*/

    private val fragmentContainer: HashMap<String, Fragment> = HashMap()

    private val navigationHistory: ArrayList<String> = ArrayList()

    init {
        fragmentContainer[Tab.HOME.tabName] = HomeFragment.newInstance()
        fragmentContainer[Tab.LIBRARY.tabName] = LibraryFragment.newInstance()
        fragmentContainer[Tab.BROWSE.tabName] = BrowseFragment.newInstance()
        fragmentContainer[Tab.PROFILE.tabName] = ProfileFragment.newInstance()
    }

    companion object {
        val instance: MainNavigator by lazy { Holder.INSTANCE }

        private const val TAG = "MainNavigator"
    }

    enum class Tab(val tabName: String) {
        HOME("home"),
        LIBRARY("library"),
        BROWSE("browse"),
        PROFILE("profile")
    }

    fun setActivity(host: FragmentActivity) {
        activity = WeakReference(host)
    }

    fun navigateTo(tab: MainNavigator.Tab): Boolean {
       return navigateTo(tab, null)
    }

    fun navigateTo(tab: MainNavigator.Tab, extras: Bundle?): Boolean {
        Log.d(TAG, "navigate to tab: $tab")
        val key: String = tab.tabName
        val supportFragmentManager = activity.get()?.supportFragmentManager
        return if (navigationHistory.contains(key)) {

            val destinationFragment: Fragment? = fragmentContainer[key]
            val currentFragment = supportFragmentManager?.findFragmentById(R.id.fragmentContainer)
            currentFragment?.let { supportFragmentManager.beginTransaction().remove(it).commit() }
            destinationFragment?.let { supportFragmentManager?.beginTransaction()?.add(R.id.fragmentContainer, it)?.commit() }
            navigationHistory.add(key)
            Log.d(TAG, "Fragment removed:$currentFragment")
            Log.d(TAG, "Navigated to an EXISTING tab: $destinationFragment")

            true

        } else {
            navigationHistory.add(key)
            val destinationFragment = fragmentContainer[key]
            val currentFragment = supportFragmentManager?.findFragmentById(R.id.fragmentContainer)?.let { supportFragmentManager.beginTransaction().remove(it).commit() }
            destinationFragment?.let { supportFragmentManager?.beginTransaction()?.add(R.id.fragmentContainer, it)?.commit() }
            Log.d(TAG, "Fragment removed:$currentFragment")
            Log.d(TAG, "Navigated to a NEW tab: $destinationFragment")

            false
        }
    }

/*    fun onBackPressed () :Boolean{


    }*/

}