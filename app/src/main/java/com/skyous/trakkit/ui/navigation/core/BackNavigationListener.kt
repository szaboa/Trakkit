package com.skyous.trakkit.ui.navigation.core

/**
 * Listener for back navigation used in [MainNavigator]
 *
 * @author Peter Abraham
 * @since 10/19/2018
 *
 */

interface BackNavigationListener {

    fun onNavigatedBack(tab: MainNavigator.Tab) {}
}