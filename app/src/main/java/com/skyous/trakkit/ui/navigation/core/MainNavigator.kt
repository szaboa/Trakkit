package com.skyous.trakkit.ui.navigation.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import com.skyous.trakkit.R
import com.skyous.trakkit.ui.navigation.BrowseFragment
import com.skyous.trakkit.ui.navigation.HomeFragment
import com.skyous.trakkit.ui.navigation.LibraryFragment
import com.skyous.trakkit.ui.navigation.ProfileFragment
import java.lang.IllegalStateException
import java.lang.ref.WeakReference

/**
 * Navigator for the bottom navigation
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

    private val fragmentContainer: HashMap<String, Fragment> = HashMap()

    private val navigationHistory: ArrayList<MainNavigator.Tab> = ArrayList()

    private lateinit var backNavigationListener: BackNavigationListener

    init {
        fragmentContainer[Tab.HOME.tabName] = HomeFragment.newInstance()
        fragmentContainer[Tab.LIBRARY.tabName] = LibraryFragment.newInstance()
        fragmentContainer[Tab.BROWSE.tabName] = BrowseFragment.newInstance()
        fragmentContainer[Tab.PROFILE.tabName] = ProfileFragment.newInstance()
    }

    enum class Tab(val tabName: String, val itemResId: Int) {
        HOME("home", R.id.navigation_home),
        LIBRARY("library", R.id.navigation_library),
        BROWSE("browse", R.id.navigation_browse),
        PROFILE("profile", R.id.navigation_profile)
    }

    companion object {
        val instance: MainNavigator by lazy { Holder.INSTANCE }
        private const val TAG = "MainNavigator"
    }

    fun setHost(host: FragmentActivity) {
        if (host !is BackNavigationListener)
            throw IllegalStateException("Host must implement BackNavigationListener")

        backNavigationListener = host
        activity = WeakReference(host)
    }

    /**
     * Navigates to the given MainNavigator.Tab.
     *
     * @return true if the given tab already exists in the nav history or if the given tab
     * is the same as the currently displayed, false otherwise
     */
    fun navigateTo(tab: MainNavigator.Tab): Boolean {
        return navigateTo(tab, null)
    }

    /**
     * Navigates to the given MainNavigator.Tab.
     *
     * @param extras extras to be set to the destination tab's fragment
     *
     * @return true if the given tab already exists in the nav history or if the given tab
     * is the same as the currently displayed, false otherwise
     */
    fun navigateTo(tab: MainNavigator.Tab, extras: Bundle?): Boolean {
        return navigateToInternal(tab, extras, false)
    }

    private fun navigateToInternal(tab: MainNavigator.Tab, extras: Bundle?, isBackNavigation: Boolean): Boolean {
        val key: String = tab.tabName
        val supportFragmentManager = activity.get()?.supportFragmentManager

        return if (!navigationHistory.isEmpty() && tab == navigationHistory[navigationHistory.lastIndex]) {
            //trying to navigate to the same tab
            false
        } else if (navigationHistory.contains(tab)) {
            //reopen existing tab
            Log.d(TAG, "Fragment removed:${navigationHistory[navigationHistory.lastIndex]}")
            replaceFragment(key, supportFragmentManager)
            updateNavigationHistory(tab, isBackNavigation)
            Log.d(TAG, "Navigated to an EXISTING tab: ${tab.tabName}")

            false

        } else {
            //navigating to a new tab
            if (!navigationHistory.isEmpty()) {
                Log.d(TAG, "Fragment removed:${navigationHistory[navigationHistory.lastIndex]}")
            }
            updateNavigationHistory(tab, isBackNavigation)
            replaceFragment(key, supportFragmentManager)
            Log.d(TAG, "Navigated to an EXISTING tab: ${tab.tabName}")

            true
        }
    }

    private fun updateNavigationHistory(tab: MainNavigator.Tab, isBackNavigation: Boolean) {
        if (isBackNavigation) {
            navigationHistory.removeAt(navigationHistory.lastIndex)
        } else {
            navigationHistory.add(tab)
        }
    }

    private fun replaceFragment(key: String, supportFragmentManager: FragmentManager?) {
        val destinationFragment = fragmentContainer[key]
        supportFragmentManager?.findFragmentById(R.id.fragmentContainer)?.let { supportFragmentManager.beginTransaction().remove(it).commitNowAllowingStateLoss() }
        destinationFragment?.let { supportFragmentManager?.beginTransaction()?.add(R.id.fragmentContainer, it)?.commitNowAllowingStateLoss() }
    }

    /**
     * Function to be called whenever the activity's on back press is called.
     * @return true if the back navigation was handled, false otherwise
     * False value means that host should be handle back navigation
     */
    fun onBackPressed(): Boolean {
        return when {
            navigationHistory.size > 1 -> {
                val tabToNavigate = navigationHistory[navigationHistory.lastIndex - 1]
                navigateToInternal(tabToNavigate, null, true)
                backNavigationListener?.onNavigatedBack(tabToNavigate)
                true
            }
            navigationHistory.size == 1 -> false
            else -> true
        }
    }

}