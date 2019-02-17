package com.skyous.trakkit.ui.navigation.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import com.skyous.trakkit.R
import com.skyous.trakkit.ui.explore.ExploreFragment
import com.skyous.trakkit.ui.home.HomeFragment
import com.skyous.trakkit.ui.library.LibraryFragment
import com.skyous.trakkit.ui.profile.ProfileFragment
import java.lang.ref.WeakReference

/**
 * Navigator for the bottom navigation
 *
 * @author Peter Abraham
 * @since 10/18/2018
 *
 */

class MainNavigator {

    companion object {
        const val TAG = "MainNavigator"
    }

    enum class Tab(val tabName: String, val itemResId: Int) {
        HOME("home", R.id.navigation_home),
        LIBRARY("library", R.id.navigation_library),
        EXPLORE("explore", R.id.navigation_explore),
        PROFILE("profile", R.id.navigation_profile);

        companion object {
            fun fromItemResourceId(itemResId: Int): Tab {
                for (value in values()) {
                    if (value.itemResId == itemResId) {
                        return value
                    }
                }
                throw UnsupportedOperationException("Unknown Tab resource ID")
            }
        }
    }

    private var hostActivity: WeakReference<FragmentActivity>? = null
    private var backNavigationListener: BackNavigationListener? = null

    private val fragmentContainer: HashMap<String, Fragment> = HashMap()
    private val navigationHistory: ArrayList<MainNavigator.Tab> = ArrayList()

    /**
     * Method to be called from the consumer Activity's onCreate method.
     * The passed fragment activity should also implement [BackNavigationListener],
     * otherwise Illegal state exception will be thrown.
     *
     * Sets initial tab or restores last displayed tab (before process was killed)
     * if the passed bundle contains the necessary data.
     */
    fun setHost(host: FragmentActivity, savedInstanceState: Bundle?) {

        if (hostActivity?.get() != null) throw java.lang.IllegalStateException("Host is already set!")

        if (host !is BackNavigationListener) throw IllegalStateException("Host must implement BackNavigationListener")

        backNavigationListener = host
        hostActivity = WeakReference(host)

        if (savedInstanceState != null && savedInstanceState.containsKey("nav_history")) {
            //restoring state
            Log.d(TAG, "Restoring navigation history.")
            navigationHistory.addAll(savedInstanceState.getSerializable("nav_history") as ArrayList<Tab>)
            navigateToInternal(navigationHistory.removeAt(navigationHistory.lastIndex), false)
        } else {
            //navigating to the default tab
            navigateToInternal(Tab.HOME, false)
        }
    }

    /**
     * Navigates to the given [MainNavigator.Tab].
     *
     * @param extras extras to be set to the destination tab's fragment
     *
     * @return true if the given tab already exists in the nav history or if the given tab
     * is the same as the currently displayed, false otherwise
     */
    fun navigateTo(tab: MainNavigator.Tab, extras: Bundle? = null): Boolean {
        return navigateToInternal(tab, false, extras)
    }

    /**
     * Navigates to the MainNavigator.Tab with the given resource ID
     *
     * @param extras extras to be set to the destination tab's fragment
     *
     * @return true if the given tab already exists in the nav history or if the given tab
     * is the same as the currently displayed, false otherwise
     */
    fun navigateTo(tabResourceId: Int, extras: Bundle? = null): Boolean {
        return navigateToInternal(MainNavigator.Tab.fromItemResourceId(tabResourceId), false, extras)
    }

    /**
     * Function to be called from host's [FragmentActivity.onBackPressed] method.
     *
     * @return true if the back navigation was handled, false otherwise
     * False value means that host should handle back navigation
     */
    fun onBackPressed(): Boolean {
        return when {
            navigationHistory.size > 1 -> {
                val tabToNavigate = navigationHistory[navigationHistory.lastIndex - 1]
                Log.d(TAG, "Navigating back...")
                navigateToInternal(tabToNavigate, true)
                backNavigationListener?.onNavigatedBack(tabToNavigate)
                true
            }
            else -> false
        }
    }

    /**
     * If the navigator should save it's state before process death, this
     * hostActivity must be called, passing the outState bundle.
     */
    fun onSaveInstanceState(outSate: Bundle?) {
        Log.d(TAG, "Saving navigation history:" + navigationHistory.toString())
        outSate?.putSerializable("nav_history", navigationHistory)
    }

    fun onDestroy() {
        navigationHistory.clear()
        fragmentContainer.clear()
        backNavigationListener = null
        hostActivity?.clear()
    }

    private fun navigateToInternal(tab: MainNavigator.Tab, isBackNavigation: Boolean, extras: Bundle? = null): Boolean {
        val supportFragmentManager = hostActivity?.get()?.supportFragmentManager

        return if (!navigationHistory.isEmpty() && tab == navigationHistory[navigationHistory.lastIndex]) {
            //trying to navigate to the same tab
            false
        } else if (navigationHistory.contains(tab)) {
            //reopen existing tab
            Log.d(TAG, "Fragment removed:${navigationHistory[navigationHistory.lastIndex]}")
            replaceFragment(tab, supportFragmentManager, null)
            updateNavigationHistory(tab, isBackNavigation)
            Log.d(TAG, "Navigated to an EXISTING tab: ${tab.tabName}")

            false
        } else {
            //navigating to a new tab
            if (!navigationHistory.isEmpty()) {
                Log.d(TAG, "Fragment removed:${navigationHistory[navigationHistory.lastIndex]}")
            }
            updateNavigationHistory(tab, isBackNavigation)
            replaceFragment(tab, supportFragmentManager, extras)
            Log.d(TAG, "Navigated to an NEW tab: ${tab.tabName}")

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

    private fun replaceFragment(tab: MainNavigator.Tab, supportFragmentManager: FragmentManager?, extras: Bundle?) {

        val destinationFragment = getFragmentForTab(tab)
        if (extras != null) {
            destinationFragment.arguments = extras
        }
        supportFragmentManager?.findFragmentById(R.id.fragmentContainer)?.let { supportFragmentManager.beginTransaction().remove(it).commitNowAllowingStateLoss() }
        destinationFragment.let { supportFragmentManager?.beginTransaction()?.add(R.id.fragmentContainer, it)?.commitNowAllowingStateLoss() }
    }

    private fun getFragmentForTab(tab: MainNavigator.Tab): Fragment {
        if (!fragmentContainer.containsKey(tab.tabName)) {
            Log.d(TAG, "Creating new Fragment for tab: $tab")
            when (tab) {
                MainNavigator.Tab.HOME -> fragmentContainer[tab.tabName] = HomeFragment()
                MainNavigator.Tab.LIBRARY -> fragmentContainer[tab.tabName] = LibraryFragment()
                MainNavigator.Tab.EXPLORE -> fragmentContainer[tab.tabName] = ExploreFragment()
                MainNavigator.Tab.PROFILE -> fragmentContainer[tab.tabName] = ProfileFragment()
            }
        } else {
            Log.d(TAG, "Fragment for tab: $tab -  already exists!")
        }
        return fragmentContainer[tab.tabName]!!
    }

}